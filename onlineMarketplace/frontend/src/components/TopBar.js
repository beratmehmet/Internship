import React, { useEffect, useRef, useState } from 'react'
import logo from '../assets/images.png';
import { Link } from 'react-router-dom';
import { useTranslation } from 'react-i18next';
import { useDispatch, useSelector } from 'react-redux';
import { logoutSuccess } from '../redux/authActions';
import ProfileImageWithDefault from '../components/ProfileImageWithDefault';
import SellerSubmit from '../pages/SellerSubmit';
const TopBar = () => {
    const { t } = useTranslation();
    const { username, isLoggedIn, fullName, image, isAdmin } = useSelector((store) => ({
        isLoggedIn: store.isLoggedIn,
        username: store.username,
        fullName: store.fullName,
        image: store.image,
        isAdmin:store.isAdmin
    }));
    const [addSeller, setAddSeller] = useState(false);

    const menuArea = useRef();

    const [menuVisible, setMenuVisible] = useState(false);

    useEffect(() => {
        document.addEventListener('click', menuClickTracker)
        return () => {
            document.removeEventListener('click', menuClickTracker);
        }
    }, [isLoggedIn])

    const menuClickTracker = (event) => {
        if (menuArea.current === null || menuArea.current === undefined || !menuArea.current.contains(event.target)) {
            setMenuVisible(false);
        }
    }

    const onClickParent = () => {
        setAddSeller(false);
    }

    const dispatch = useDispatch();

    const onLogoutSuccess = () => {
        dispatch(logoutSuccess())
    }

    let links = (
        <ul className="navbar-nav ms-auto">

            <li>
                <Link className="nav-link" to="/login">
                    {t('Login')}
                </Link>

            </li>
        </ul>
    );

    if (isLoggedIn) {
        let dropdownClass = 'dropdown-menu p-0 shadow';
        if (menuVisible) {
            dropdownClass += ' show';
        }
        links = (
            <>

                <ul className="navbar-nav">
                    <li className="nav-item">

                        <div className="d-flex" style={{ cursor: "pointer" }}>


                            {isAdmin && <Link className="nav-link" to={'/users'}>
                                {t('Users')}
                            </Link>}
                            {isAdmin && <Link className="nav-link" to={'/signup'}>
                                {t('Add User')}
                            </Link>}
                            <Link className="nav-link" to={'/products'}>
                                {t('Products')}
                            </Link>
                            {isAdmin && <Link className="nav-link" to={'/addproduct'}>
                                {t('Add Product')}
                            </Link>}
                            <Link className="nav-link" to={'/sellers/'}>
                                {t('Sellers')}
                            </Link>
                            {isAdmin && <i className="nav-link" onClick={() => { setAddSeller(!addSeller) }}>
                                {t('Add Seller')}
                            </i>}
                            {addSeller && <SellerSubmit onClickParent={onClickParent} addSeller={addSeller} />}


                        </div>
                    </li>
                </ul>

                <ul className="navbar-nav ms-auto" ref={menuArea}>

                    <li className="nav-item dropdown">

                        <div className="d-flex" style={{ cursor: "pointer" }}
                            onClick={() => { setMenuVisible(true) }}
                        >
                            <ProfileImageWithDefault className="rounded-circle m-auto" image={image} width="32" height="32" />
                            <span className="nav-link dropdown-toggle">{fullName}</span>
                        </div>
                        <div className={dropdownClass}>

                            <Link className="dropdown-item d-flex p-2" to={'/user/' + username} onClick={() => { setMenuVisible(false) }}>
                                <span className="material-icons text-info me-2">
                                    person
                                </span>
                                {t('My Profile')}
                            </Link>

                            <span>
                                <Link className="dropdown-item d-flex p-2" to="/" onClick={onLogoutSuccess} style={{ cursor: "pointer" }}>
                                    <span className="material-icons text-danger me-2">
                                        power_settings_new
                                    </span>
                                    {t("Logout")}
                                </Link>
                            </span>
                        </div>

                    </li>

                </ul>
            </>
        );
    }

    return (
        <div className="shadow-sm  bg-light mb-2">
            <nav className="navbar navbar-light container navbar-expand">
                <Link className="navbar-brand" to="/">
                    <img src={logo} width="60" alt="Obss Logo" />
                    Marketplace
                </Link>
                {links}
            </nav>
        </div>
    );

}


export default TopBar;