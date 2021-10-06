import React, { useState, useEffect } from 'react';
import { useApiProgress } from '../shared/ApiProgress';
import SellerListItem from './SellerListItem';
import { getSellers, searchSellers } from '../api/apiCalls';
import { useTranslation } from 'react-i18next';
import Spinner from './Spinner';
import './table.css'
import Input from './Input';
import { useSelector } from 'react-redux';

const SellerList = () => {
    const [page, setPage] = useState({
        content: [],
        size: 3,
        number: 0
    })

    const { isAdmin } = useSelector((store) => ({isAdmin:store.isAdmin}))

    const [search, setSearch] = useState();

    const [loadFailure, setloadFailure] = useState(false);

    const pendingApiCall = useApiProgress('get', '/api/1.0/users?page');

    let btnText;

    useEffect(() => {
        loadSellers();
    }, [search]);

    const onClickNext = () => {
        const nextPage = page.number + 1;
        loadSellers(nextPage);
    }

    const onClickPrevious = () => {
        const previousPage = page.number - 1;
        loadSellers(previousPage);
    }

    const loadSellers = async page => {
        setloadFailure(false);
        try {
            if (search === "" || search === undefined || search === null) {
                const response = await getSellers(page);
                setPage(response.data);
            }else{
                const body = {
                    name: search,
                }
                const response = await searchSellers(page, body)
                setPage(response.data);
            }
            
        } catch (error) {
            setloadFailure(true);
        }
    }

    btnText = isAdmin ? 'Edit' : 'Go to seller';



    const { content: sellers, last, first } = page;
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
                <caption>List of Sellers</caption>
                <thead>
                    <tr>
                        <th scope="col">id</th>
                        <th scope="col">{t('Name')}</th>
                        <th scope="col"></th>
                    </tr>
                </thead>
                <tbody>
                    {
                        sellers.map(seller => (
                            <SellerListItem key={seller.id} seller={seller} btnText={btnText} />
                        ))
                    }
                </tbody>
            </table>
            {actionDiv}
            {loadFailure && <div className="text-center text-danger">{t('Load Failure')}</div>}
        </div>
    );

};

export default SellerList;