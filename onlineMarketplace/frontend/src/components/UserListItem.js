import React from 'react';
import { useTranslation } from 'react-i18next';
import { Link } from 'react-router-dom';
const UserListItem = (props) => {
    const { user } = props;
    const { username, fullName, id, email } = user;
    const {t}= useTranslation();

    return (

        <tr>
            <th>{id}</th>
            <td>{username}</td>
            <td>{fullName}</td>
            <td>{email}</td>
            <td>
                <Link className="btn btn-primary" to={`/user/${username}`}>
                    {t('Edit')}
                </Link>
            </td>
        </tr>



    );
};

export default UserListItem;