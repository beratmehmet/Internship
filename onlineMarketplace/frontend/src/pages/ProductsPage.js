import React from 'react';
import ProductList from '../components/ProductList'

const ProductsPage = () => {
    return (
        <div className="container">
            <div className="row">
                <div className="col mt-5"><ProductList/></div>
            </div>
        </div>
    );
};

export default ProductsPage;