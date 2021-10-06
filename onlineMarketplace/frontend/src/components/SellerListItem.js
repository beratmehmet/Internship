import React from 'react';
import { useTranslation } from 'react-i18next';
import { Link } from 'react-router-dom';
const SellerListItem = (props) => {
    const {seller, btnText}=props;
    const {id,name}=seller;
    const {t}=useTranslation();
    return (
        <tr>
            <th>{id}</th>
            <td>{name}</td>
            <td>
                <Link className="btn btn-primary" to={`/seller/${id}`}>
                    {t(btnText)}
                </Link>
            </td>
        </tr>
    );
};

export default SellerListItem;