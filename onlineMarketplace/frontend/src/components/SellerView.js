import React, { useState } from 'react';
import { useSelector } from 'react-redux';
import { Link } from 'react-router-dom';
import { addSellerToBlackList, removeSellerFromBlackList } from '../api/apiCalls';

const SellerView = (props) => {
    const { username: loggedInUsername } = useSelector((store) => ({ username: store.username }));
    const { seller } = props;
    const [btnClassName, setBtnClassName] = useState('clear');

    const onClickClear = async () => {
        if (btnClassName === 'clear') {
            await removeSellerFromBlackList(loggedInUsername, seller.id);
            setBtnClassName('add');
        } else {
            await addSellerToBlackList(loggedInUsername, seller.id)
            setBtnClassName('clear');
        }

    }

    return (
        <div className="card p-1">
            <div className="d-flex">
                <div className="flex-fill m-auto ps-3">
                    <Link to={`/seller/${seller.id}`}>
                        <h6 className="d-inline">
                            {seller.name}
                        </h6>
                    </Link>
                </div>

                <button className="btn btn-delete-link btn-sm"
                    onClick={onClickClear}
                >
                    <i className="material-icons">
                        {btnClassName}
                    </i>
                </button>
            </div>
        </div>
    );
};

export default SellerView;