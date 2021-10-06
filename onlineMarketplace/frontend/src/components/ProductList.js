import React, { useEffect, useState } from 'react';
import ProductListItem from './ProductListItem';
import { getProducts, searchProducts } from '../api/apiCalls';
import { useTranslation } from 'react-i18next';
import { useApiProgress } from '../shared/ApiProgress';
import Spinner from './Spinner';
import './table.css'
import Input from './Input';
import { useSelector } from 'react-redux';
const ProductList = () => {
    const [page, setPage] = useState({
        content: [],
        size: 3,
        number: 0
    })

    const { isAdmin } = useSelector((store) => ({isAdmin:store.isAdmin}))

    const [search,setSearch]=useState();

    const [loadFailure, setloadFailure] = useState(false);

    const pendingApiCall = useApiProgress('get', '/api/1.0/products?page');

    useEffect(() => {
        loadProducts();
    }, [search]);

    const onClickNext = () => {
        const nextPage = page.number + 1;
        loadProducts(nextPage);
    }

    const onClickPrevious = () => {
        const previousPage = page.number - 1;
        loadProducts(previousPage);
    }

    const loadProducts = async page => {
        setloadFailure(false);
        if(search==="" || search===undefined || search===null){
            try {
                const response = await getProducts(page);
                setPage(response.data);
            } catch (error) {
                setloadFailure(true);
            }
        }else{
            const body = {
                name: search,
            }
            try{
                const response = await searchProducts(page,body);
                setPage(response.data)
            }catch (error) {
                setloadFailure(true);
            }
        }
        
        
    }

    

    const { content: products, last, first } = page;
    const { t } = useTranslation();
    let btnText= isAdmin ? 'Edit' : 'Go to product';
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
                label={t("Search")+":"} 
                onChange={(event)=>{setSearch(event.target.value)}} />
            </div>

            <table className="table table-striped table-hover">
                <thead>
                    <tr>
                        <th scope="col">id</th>
                        <th scope="col">{t('Product Name')}</th>
                        <th scope="col">{t('username')}</th>
                        <th scope="col">{t('Seller')}</th>
                        <th scope="col">{t('Price')}</th>
                        <th scope="col"></th>
                    </tr>
                </thead>
                <tbody>
                    {
                        products.map(product => (
                            <ProductListItem key={product.id} product={product} btnText={btnText} />


                        ))
                    }
                </tbody>
            </table>
            {actionDiv}
            {loadFailure && <div className="text-center text-danger">{t('Load Failure')}</div>}
        </div>
    );
};

export default ProductList;