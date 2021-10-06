import React from 'react';

const SellerDropDown = (props) => {
    const {seller}=props;
    const {name, id}=seller
    return (
        
            <option value={id}>{name}</option>
        
    );
};

export default SellerDropDown;