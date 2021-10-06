import React, { useEffect, useState } from 'react';
import { useTranslation } from 'react-i18next';
import { getProducts, getOldProducts, getProductsOfSeller, getUser } from '../api/apiCalls';
import { useApiProgress } from '../shared/ApiProgress';
import ProductView from './ProductView';
import Spinner from './Spinner';
import { useParams } from 'react-router';
import { useSelector } from 'react-redux';

const ProductFeed = () => {
    const { username: loggedInUsername } = useSelector((store) => ({ username: store.username }))
    const [productPage, setProductPage] = useState({ content: [], last: true, number: 0 });
    const [user, setUser] = useState({});
    const { id } = useParams();
    const { t } = useTranslation();
    const path = id ? `/api/1.0/users/${id}/products?page=` : '/api/1.0/products?page=';

    const initialProductLoadProgress = useApiProgress('get', path);

    let lastProductId = 0;
    if (productPage.content.length > 0) {
        const lastProductIndex = productPage.content.length - 1;
        lastProductId = productPage.content[lastProductIndex].id;
    }
    const oldProductpath = id ? `/api/1.0/users/${id}/products/${lastProductId}` : `/api/1.0/products/${lastProductId}`;
    const loadOldProductsProgress = useApiProgress('get', oldProductpath, true);



    useEffect(() => {
        const loadProducts = async (page) => {
            let response;
            try {
                if (id) {
                    response = await getProductsOfSeller(id, page);
                } else {
                    response = await getProducts(page);
                   
                }

                if (loggedInUsername) {
                    const userResponse = await getUser(loggedInUsername);
                    setUser(userResponse.data)
                }

                setProductPage(response.data);
                

            } catch (error) { }
        };
        loadProducts();
    }, [id,loggedInUsername])

    const loadOldProducts = async () => {

        const response = await getOldProducts(id, lastProductId);

        setProductPage(previousProductPage => ({
            ...response.data,
            content: [...previousProductPage.content, ...response.data.content]
        }));
    }

    const onDeleteProduct = (id) => {
        setProductPage(previousProductPage => ({
            ...previousProductPage,
            content: previousProductPage.content.filter((product) => product.id !== id)
        }))
    }


    const { content } = productPage;
    if (content.length === 0) {
        return <div className="alert alert-secondary text-center">{initialProductLoadProgress ? <Spinner /> : t('There are no products')}</div>
    }

    let liked = false;

    return (
        <div>
            {content.map(product => {
                if(loggedInUsername){
                    if (user.favs.filter(e => e.id === product.id).length > 0) {
                        liked = true;

                    }
                    else {
                        liked = false;
                    }
                }
                return <ProductView key={product.id} product={product} onDeleteProduct={onDeleteProduct} liked={liked} />
            })}
            {!productPage.last &&
                <div
                    className="alert alert-secondary text-center"
                    style={{ cursor: loadOldProductsProgress ? 'not-allowed' : "pointer" }}
                    onClick={loadOldProductsProgress ? () => { } : loadOldProducts}
                >
                    {loadOldProductsProgress ? <Spinner /> : t('Load old products')}
                </div>
            }
        </div>
    );
};

export default ProductFeed;