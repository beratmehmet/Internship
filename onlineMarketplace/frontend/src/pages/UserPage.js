import React, { useEffect, useState } from 'react';
import { useTranslation } from 'react-i18next';
import { useParams } from 'react-router';
import { getUser } from '../api/apiCalls';
import ProductView from '../components/ProductView';
import ProfileCard from '../components/ProfileCard';
import SellerView from '../components/SellerView';
import Spinner from '../components/Spinner';
import { useApiProgress } from '../shared/ApiProgress';

const UserPage = () => {
    const [user, setUser] = useState({});
    const [notFound, setNotFound] = useState(false);
    let links;
    let linksBlacklist;
    const { username } = useParams();
    const { t } = useTranslation();
    const pendingApiCall = useApiProgress('get', '/api/1.0/users/' + username, true);


    useEffect(() => {
        setNotFound(false);
    }, [user]);

    useEffect(() => {
        const loadUser = async () => {
            try {
                const response = await getUser(username);
                setUser(response.data);
            } catch (error) {
                setNotFound(true);
            }
        };

        loadUser();

    }, [username]);

    if (notFound) {
        return (
            <div className="">
                <div className="alert alert-danger text-center" role="alert">
                    <div>
                        <span className="material-icons" style={{ fontSize: '48px' }}>error</span>
                    </div>
                    {t('User Not Found!')}
                </div>
            </div>
        );
    }

    if (pendingApiCall || user.username !== username) {
        return (
            <Spinner />
        );
    }

    
    if (user.favs.length === 0) {
        links = (<div className="alert alert-secondary text-center">{pendingApiCall ? <Spinner /> : t('There are no favourite products')}</div>)
    } else {
        links = (<div>
            <h4>Favourite List</h4>
            {user.favs.map(product => {
                
                return <ProductView key={product.id} product={product} user={user} liked={true} />
            })}
        </div>)
    }

    if(user.blackList.length === 0){
        linksBlacklist = (<div className="alert alert-secondary text-center">{pendingApiCall ? <Spinner /> : t('Blacklist is empty')}</div>)
    }else{
        linksBlacklist = (<div>
            <h4>Blacklist</h4>
            {user.blackList.map(seller => {
                return <SellerView key={seller.id} seller={seller} loggedInUsername={username}/>
            })}
        </div>)
    }

    return (
        <div className="container">
            <div className="row">
                <div className="col">
                    <ProfileCard user={user} />
                    {linksBlacklist}
                </div>
                <div className="col">
                {links}
                </div>
                
            </div>

        </div>
    );
};

export default UserPage;