import React, { useState, useEffect } from 'react';
import { useTranslation } from 'react-i18next';
import { useHistory } from 'react-router';
import { addSellerToBlackList, deleteSeller, updateSeller } from '../api/apiCalls';
import ProfileImageWithDefault from './ProfileImageWithDefault';
import Modal from '../components/Modal';
import { useApiProgress } from '../shared/ApiProgress';
import Input from './Input';
import ButtonWithProgress from './ButtonWithProgress';
import { useSelector } from 'react-redux';
import { toast } from 'react-toastify';


const SellerCard = (props) => {
    const { username: loggedInUsername, isAdmin } = useSelector((store) => ({ username: store.username, isAdmin: store.isAdmin }))
    const [seller, setSeller] = useState({})
    const [inEditMode, setInEditMode] = useState(false);
    const [updatedName, setUpdatedName] = useState();
    const [modalVisible, setModalVisible] = useState(false);
    const [validationErrors, setValidationErrors] = useState({});
    const { id, name } = seller;
    const { t } = useTranslation();
    const history = useHistory();

    useEffect(() => {
        setSeller(props.seller)
    }, [props.seller])

    useEffect(() => {
        if (!inEditMode) {
            setUpdatedName(undefined);
        } else {
            setUpdatedName(name)
        }

    }, [inEditMode, name])

    useEffect(() => {
        setValidationErrors((previousValidationErrors) => ({
            ...previousValidationErrors,
            name: undefined
        }))
    }, [name])

    const onClickSave = async () => {
        const body = {
            name: updatedName,
        };

        try {
            const response = await updateSeller(id, body);
            setInEditMode(false);
            setSeller(response.data);
            window.location.reload();

        } catch (error) {
            setValidationErrors(error.response.data.validationErrors);
        }
    }

    const onClickDeleteSeller = async () => {
        await deleteSeller(id);
        setModalVisible(false);
        history.push('/sellers')
    }
    const onClickCancel = () => {
        setModalVisible(false);
    }

    const onClickBlacklist = async () => {
        await addSellerToBlackList(loggedInUsername, seller.id)
        toast(t('Seller added to blacklist'))
    }

    const pendingApiCall = useApiProgress('put', '/api/1.0/sellers/' + id, true);
    const { name: nameError } = validationErrors;
    return (
        <div className="row">
            <div className="col">
                <div className="card text-center">
                    <div className="card-header">

                        <ProfileImageWithDefault
                            className="rounded-circle shadow"
                            width="200"
                            height="200"
                            alt={` profile`}

                        />
                    </div>
                    <div className="card-body bg-light">
                        <h3>{name}</h3>
                        {isAdmin &&
                            <>
                                {!inEditMode &&
                                    <>
                                        <button className="btn btn-success d-inline-flex"
                                            onClick={() => { setInEditMode(true) }}>
                                            <i className="material-icons">edit</i>
                                            {t('Edit')}
                                        </button>
                                        <button className="btn btn-danger d-inline-flex"
                                            onClick={() => { setModalVisible(true) }}>
                                            <i className="material-icons">directions_run</i>
                                            {t('Delete Seller')}
                                        </button>

                                    </>
                                }
                                {inEditMode &&
                                    <div>
                                        <Input label={t("Change Name")}
                                            defaultValue={name}
                                            onChange={(event) => {
                                                setUpdatedName(event.target.value);
                                            }}
                                            error={nameError}
                                        />
                                        <ButtonWithProgress className="btn btn-primary d-inline-flex"
                                            onClick={onClickSave}
                                            disabled={pendingApiCall}
                                            pendingApiCall={pendingApiCall}
                                            text={
                                                <>
                                                    <i className="material-icons">save</i>
                                                    {t('Save')}
                                                </>
                                            }
                                        />
                                    </div>


                                }
                                <div>
                                
                                </div>
                               
                            </>
                        }
                        <button className="btn btn-dark d-inline-flex"
                                    onClick={onClickBlacklist}>
                                    {t('Add to Blacklist')}
                        </button>
                    </div>
                </div>
            </div>
            <Modal
                visible={modalVisible}
                title={t('Delete Seller')}
                okButton={t('Delete Seller')}
                onClickCancel={onClickCancel}
                onClickOk={onClickDeleteSeller}
                message={
                    <div>
                        <div>
                            <strong>{t('Are you sure to delete this seller?')}</strong>
                        </div>
                        <span></span>
                    </div>
                }

            />
        </div>

    );
};

export default SellerCard;