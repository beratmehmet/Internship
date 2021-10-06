import React, { useEffect, useState } from 'react';
import { getUsers, searchUsers } from '../api/apiCalls';
import { useTranslation } from 'react-i18next';
import UserListItem from './UserListItem';
import { useApiProgress } from '../shared/ApiProgress';
import Spinner from './Spinner';
import './table.css'
import Input from './Input';

const UserList = (props) => {

    const [page, setPage] = useState({
        content: [],
        size: 10,
        number: 0
    })

    const [search, setSearch] = useState();

    const [loadFailure, setloadFailure] = useState(false);

    const pendingApiCall = useApiProgress('get', '/api/1.0/users?page');

    useEffect(() => {
        loadUsers();
    }, [search]);

    const onClickNext = () => {
        const nextPage = page.number + 1;
        loadUsers(nextPage);
    }

    const onClickPrevious = () => {
        const previousPage = page.number - 1;
        loadUsers(previousPage);
    }

    const loadUsers = async page => {
        setloadFailure(false);
        try {
            if (search === "" || search === undefined || search === null) {
                const response = await getUsers(page);
                setPage(response.data);
            } else {
                const body = {
                    name: search,
                }
                const response = await searchUsers(page, body)
                setPage(response.data);
            }

        } catch (error) {
            setloadFailure(true);
        }
    }

    const { content: users, last, first } = page;
    const { t } = useTranslation();

    let actionDiv = (
        <div>
            {first === false && <button className="btn btn-sm btn-light" onClick={onClickPrevious}>{t('Previous')}</button>}
            {last === false && <button className="btn btn-sm btn-light float-end" onClick={onClickNext}>{t('Next')}</button>}
        </div>
    );

    if (pendingApiCall) {
        actionDiv = (
            <Spinner />
        );
    }
    return (
        <div>
            <div>
                <Input
                    label={t("Search") + ":"}
                    onChange={(event) => { setSearch(event.target.value) }} />
            </div>
            <table className="table table-striped table-hover">
                <thead>
                    <tr>
                        <th scope="col">{t('id')}</th>
                        <th scope="col">{t('username')}</th>
                        <th scope="col">{t('Name')}</th>
                        <th scope="col">email</th>
                        <th scope="col"></th>
                    </tr>
                </thead>
                <tbody>
                    {
                        users.map(user => (
                            <UserListItem key={user.username} user={user} />
                        ))
                    }
                </tbody>
            </table>
            {actionDiv}
            {loadFailure && <div className="text-center text-danger">{t('Load Failure')}</div>}
        </div>
    );

}

export default (UserList);