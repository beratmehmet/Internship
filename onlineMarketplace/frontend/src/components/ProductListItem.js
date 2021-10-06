import React from 'react';
import { useTranslation } from 'react-i18next';
import { Link } from 'react-router-dom';
const ProductListItem = (props) => {
    const {product, btnText}=props;
    const{content, id, name, seller, price}= product;
    const {t}=useTranslation();
    
    return (
        <tr>
            <th>{id}</th>
            <td>{name}</td>
            <td>{content}</td>
            <td>{seller.name}</td>
            <td>{price}</td>
            <td>
                <Link className="btn btn-primary" to={`/product/${id}`}>
                    {t(btnText)}
                </Link>
            </td>
        </tr>
            
            
        
    );
};

export default ProductListItem;