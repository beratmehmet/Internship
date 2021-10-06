import React, { useEffect, useState } from 'react';
import { useHistory, useParams } from 'react-router';
import { useSelector, useDispatch } from 'react-redux';
import ProfileImageWithDefault from './ProfileImageWithDefault';
import { useTranslation } from 'react-i18next';
import Input from '../components/Input';
import { changeUserPassword, deleteUser, updateUser } from '../api/apiCalls';
import { useApiProgress } from '../shared/ApiProgress';
import ButtonWithProgress from './ButtonWithProgress';
import { logoutSuccess, updateSuccess } from '../redux/authActions';
import Modal from './Modal';



const ProfileCard = (props) => {

    const { username: loggedInUsername } = useSelector((store) => ({ username: store.username }))
    const [inEditMode, setInEditMode] = useState(false);
    const [changePassword, setChangePassword] = useState(false);
    const [updatedFullName, setUpdatedFullName] = useState();
    const [updatedEmail, setUpdatedEmail] = useState();
    const [updatedPassword, setUpdatedPassword] = useState();

    const routeParams = useParams();
    const [user, setUser] = useState({});
    const [editable, setEditable] = useState(false);
    const [validationErrors, setValidationErrors] = useState({});
    const [modalVisible, setModalVisible] = useState(false);
    const { t } = useTranslation();
    const pathUsername = routeParams.username;
    const { username, fullName, email, image } = user;
    const pendingApiCallDeleteUser = useApiProgress('delete', `/api/1.0/users/${username}`, true);
    const dispatch = useDispatch();
    const history = useHistory();

    useEffect(() => {
        setUser(props.user)
    }, [props.user])

    useEffect(() => {
        setEditable(pathUsername === loggedInUsername)
    }, [pathUsername, loggedInUsername])

    useEffect(() => {
        setValidationErrors((previousValidationErrors) => ({
            ...previousValidationErrors,
            fullName: undefined
        }))
    }, [updatedFullName])

    useEffect(() => {
        setValidationErrors((previousValidationErrors) => ({
            ...previousValidationErrors,
            email: undefined
        }))
    }, [updatedEmail])


    useEffect(() => {
        if (!inEditMode) {
            setUpdatedFullName(undefined);
            setUpdatedEmail(undefined);
        } else {
            setUpdatedFullName(fullName)
            setUpdatedEmail(email);
        }

    }, [inEditMode, fullName, email])

    const onClickSave = async () => {
        const body = {
            fullName: updatedFullName,
            email: updatedEmail,
        };

        try {
            const response = await updateUser(username, body);
            setInEditMode(false);
            setUser(response.data);
            if (editable) {
                dispatch(updateSuccess(response.data));
            }

        } catch (error) {
            setValidationErrors(error.response.data.validationErrors);
        }
    }

    const onClickCancel = () => {
        setModalVisible(false);
    }

    const onClickDeleteUser = async () => {
        await deleteUser(username);
        setModalVisible(false);
        if (editable) {
            dispatch(logoutSuccess());
            history.push('/')

        } else {
            history.push('/users')
        }


    }

    const onClickChangePassword = async() =>{
        const body = {
            password:updatedPassword
        };

        try {
            await changeUserPassword(username, body);
            setChangePassword(false);
        } catch (error) {
            setValidationErrors(error.response.data.validationErrors);
        }
    }


    const pendingApiCall = useApiProgress('put', '/api/1.0/users/' + username);
    const { fullName: fullNameError, email: emailError, password: passwordError } = validationErrors;

    return (
        <div className="card text-center">
            <div className="card-header">
                <ProfileImageWithDefault
                    className="rounded-circle shadow"
                    width="200"
                    height="200"
                    alt={`${username} profile`}
                    image={image}
                />

            </div>
            <div className="card-body">
                {(!inEditMode) && (
                    <>
                        <h3>{fullName}@{username}</h3>


                        <>
                            <button className="btn btn-success d-inline-flex"
                                onClick={() => { 
                                    setInEditMode(true);
                                    setChangePassword(false);
                                }}>
                                <i className="material-icons">edit</i>
                                {t('Edit')}
                            </button>
                            <button className="btn btn-success d-inline-flex ms-2"
                                onClick={() => { 
                                    setChangePassword(true);
                                    setInEditMode(false);
                                     }}>
                                <i className="material-icons"><span className="material-icons">
                                    password
                                </span></i>
                                {t('Change Password')}
                            </button>
                            <div className="pt-2"></div>
                            <button className="btn btn-danger d-inline-flex"
                                onClick={() => { setModalVisible(true) }}>
                                <i className="material-icons">directions_run</i>
                                {t('Delete Account')}
                            </button>
                        </>




                    </>
                )}
                {inEditMode && (
                    <div>
                        <Input label={t("Change Name")}
                            defaultValue={fullName}
                            onChange={(event) => {
                                setUpdatedFullName(event.target.value);
                            }}
                            error={fullNameError}
                        />
                        <Input label={t("Change email")}
                            defaultValue={email}
                            onChange={(event) => {
                                setUpdatedEmail(event.target.value);
                            }}
                            error={emailError}
                        />
                        <div>
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

                            <button className="btn btn-light d-inline-flex m-1"
                                onClick={() => { setInEditMode(false) }}
                                disabled={pendingApiCall}
                            >
                                <span className="material-icons">cancel</span>{t('Cancel')}
                            </button>

                        </div>
                    </div>
                )}
                {changePassword && (
                    <>
                        <div>
                            <Input label={t("Change password")}
                                onChange={(event) => {
                                    setUpdatedPassword(event.target.value);
                                }}
                                error={passwordError} />
                        </div>

                        <div>
                            <ButtonWithProgress className="btn btn-primary d-inline-flex"
                                onClick={onClickChangePassword}
                                disabled={pendingApiCall}
                                pendingApiCall={pendingApiCall}
                                text={
                                    <>
                                        <i className="material-icons">save</i>
                                        {t('Save')}
                                    </>
                                }
                            />

                            <button className="btn btn-light d-inline-flex m-1"
                                onClick={() => { setChangePassword(false) }}
                                disabled={pendingApiCall}
                            >
                                <span className="material-icons">cancel</span>{t('Cancel')}
                            </button>

                        </div>
                    </>
                )}
                <Modal
                    visible={modalVisible}
                    title={t('Delete Account')}
                    okButton={t('Delete Account')}
                    onClickCancel={onClickCancel}
                    onClickOk={onClickDeleteUser}
                    message={
                        <div>
                            <div>
                                <strong>{t('Are you sure to delete this account?')}</strong>
                            </div>
                            <span></span>
                        </div>
                    }
                    pendingApiCall={pendingApiCallDeleteUser}
                />
            </div>
        </div>
    );
}
export default (ProfileCard);