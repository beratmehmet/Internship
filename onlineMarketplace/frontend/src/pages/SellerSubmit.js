import React, { useState, useEffect } from 'react';
import { postSeller } from '../api/apiCalls';
import { useTranslation } from 'react-i18next';
import Input from '../components/Input';
import { useApiProgress } from '../shared/ApiProgress';
import Modal from '../components/Modal';



const SellerSubmit = (props) => {
    const [addSeller, setAddSeller] = useState(props.addSeller);
    const { onClickParent } = props
    const [name, setName] = useState();
    const [errors, setErrors] = useState({});
    const { t } = useTranslation();


    useEffect(() => {
        setErrors({});
    }, [name])

    const onClickSave = async () => {
        const body = {
            name: name,
        }
        try {
            await postSeller(body);
            setAddSeller(false);
            onClickParent();

        } catch (error) {
            if (error.response.data.validationErrors) {
                setErrors(error.response.data.validationErrors);
            }
        }
    };

    const onClickCancel = () => {
        setName('');
        setErrors({});
        setAddSeller(false);
        onClickParent();
    }

    const { name: nameError } = errors

    const pendingApiCall = useApiProgress('post', '/api/1.0/sellers', true);

    return (
        <Modal
            visible={addSeller}
            onClickOk={onClickSave}
            onClickCancel={onClickCancel}
            message={<Input label={t("Name")} error={nameError} onChange={(event) => { setName(event.target.value) }}></Input>}
            okButton={t("Save")}
            pendingApiCall={pendingApiCall}
            disabled={pendingApiCall}
        ></Modal>

    );
};

export default SellerSubmit;