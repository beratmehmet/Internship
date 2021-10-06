import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router';
import SellerCard from '../components/SellerCard';
import { getSeller } from '../api/apiCalls';
import ProductFeed from '../components/ProductFeed';
import { useTranslation } from 'react-i18next';

const SellerPage = () => {
    const [seller, setSeller] = useState({});
    const { id } = useParams();
    const [notFound, setNotFound] = useState(false);
    const {t}=useTranslation();

    useEffect(() => {
        setNotFound(false);
    }, [seller]);


    useEffect(() => {
        const loadSeller = async () => {
            try {
                const response = await getSeller(id);
                setSeller(response.data);
            } catch (error) {
                setNotFound(true);
            }
        };
        loadSeller();
    }, [id]);

    if (notFound) {
        return (
            <div className="">
                <div className="alert alert-danger text-center" role="alert">
                    <div>
                        <span className="material-icons" style={{ fontSize: '48px' }}>error</span>
                    </div>
                    {t('Seller Not Found!')}
                </div>
            </div>
        );
    }
    return (
        <div className="container">
            <div className="row">
                <div className="col">
                    <SellerCard seller={seller} />
                </div>
                <div className="col">
                    <ProductFeed />
                </div>
            </div>

        </div>

    );
};

export default SellerPage;