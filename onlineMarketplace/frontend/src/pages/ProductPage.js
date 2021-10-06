import React, { useState, useEffect } from 'react';
import { useTranslation } from 'react-i18next';
import { useParams } from 'react-router';
import { getProduct } from '../api/apiCalls';
import ProductCard from '../components/ProductCard';
const ProductPage = () => {

    const [product, setProduct] = useState({});
    const [seller, setSeller] = useState({})
    const [notFound, setNotFound] = useState(false);
    const { id } = useParams();
    const {t}=useTranslation();


    useEffect(() => {
        const loadProduct = async () => {
            try {
                const response = await getProduct(id);
                setProduct(response.data);
                setSeller(response.data.seller)
            } catch (error) {
                setNotFound(true);
            }
        };
        loadProduct();
    }, [id]);

    if (notFound) {
        return (
            <div className="">
                <div className="alert alert-danger text-center" role="alert">
                    <div>
                        <span className="material-icons" style={{ fontSize: '48px' }}>error</span>
                    </div>
                    {t('Product Not Found!')}
                </div>
            </div>
        );
    }


    return (
        <div>
            <ProductCard product={product} seller={seller} />
        </div>
    );
};

export default ProductPage;