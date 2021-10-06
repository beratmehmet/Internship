import React, { useEffect, useState } from 'react';
import { useTranslation } from 'react-i18next';
import { toast } from 'react-toastify';
import { getSellersWOPage, postProduct, postProductAttachment } from '../api/apiCalls';
import SellerSubmit from '../pages/SellerSubmit';
import { useApiProgress } from '../shared/ApiProgress';
import AutoUploadImage from './AutoUploadImage';
import ButtonWithProgress from './ButtonWithProgress';
import Input from './Input';
import SellerDropDown from './SellerDropDown';

const ProductSubmit = () => {
    const [errors, setErrors] = useState({});
    const [newImage, setNewImage] = useState('');
    const [addSeller, setAddSeller] = useState(false);
    const { t } = useTranslation();
    const [page, setPage] = useState([])

    const [form, setForm] = useState({
        content: null,
        attachmentId: null,
        name: null,
        sellerId: null,
        price: null
    })
    useEffect(() => {
        if (addSeller === false) {
            loadSellers();
        }

    }, [addSeller])

    const onChange = event => {
        const { name, value } = event.target;

        setErrors((previousErrors) => ({ ...previousErrors, [name]: undefined }));
        setForm((previousForm) => { return { ...previousForm, [name]: value } })
    }

    const onClickSave = async () => {
        if (form.sellerId === "" || form.sellerId === null) {
            return;
        }
        try {
            await postProduct(form);
            toast(t('Product added'));
            setForm({
                content: '',
                attachmentId: '',
                name: '',
                sellerId: '',
                price: ''
            })
            setNewImage('');
        } catch (error) {
            
            if (error.response.data.validationErrors) {
                setErrors(error.response.data.validationErrors);
            }
        }
    };
    const onChangeFile = (event) => {
        if (event.target.files.length < 1) {
            return;
        }
        const file = event.target.files[0];
        const fileReader = new FileReader();
        fileReader.onloadend = () => {
            setNewImage(fileReader.result);
            uploadFile(file);
        };
        fileReader.readAsDataURL(file);
    }

    const uploadFile = async (file) => {
        const attachment = new FormData();
        attachment.append('file', file);
        const response = await postProductAttachment(attachment);
        setForm((previousForm) => { return { ...previousForm, attachmentId: response.data.id } })
    }

    const loadSellers = async () => {
        try {
            const response = await getSellersWOPage();
            setPage(response.data);
        } catch (error) {
            setErrors(error.response.data.validationErrors);
        }
    }

    const onClickParent = () => {
        setAddSeller(false);
    }


    const pendingApiCall = useApiProgress('post', '/api/1.0/products', true);
    const pendingFileUpload = useApiProgress('post', '/api/1.0/product-attachments', true);
    const { name: nameError, content: contentError, price: priceError } = errors;
    return (
        <div className="container">


            <Input name="name" value={form.name} label={t("Name")} error={nameError} onChange={onChange}></Input>
            <Input name="content" value={form.content} label={t("Content")} error={contentError} onChange={onChange}></Input>
            <Input name="price" type='number' value={form.price} label={t("Price")} pattern="[0-9]*" error={priceError} onChange={onChange}></Input>
            <div className="row">
                <span className="was-validated d-flex-inline">
                    <select name="sellerId" className="btn btn-primary" id="dropdown pre-scrollable" flip="true" onChange={onChange} required>
                        <option value="">{t('Choose Seller')}</option>
                        {page.map(seller => (
                            <SellerDropDown key={seller.name} seller={seller} />
                        ))}
                    </select>

                    <span className="invalid-feedback">
                        {t('Please choose a seller')}
                    </span>
                </span>

            </div>

            <button className="btn btn-primary mt-2" onClick={() => setAddSeller(!addSeller)}>{t('Add Seller')}</button>
            {addSeller && <SellerSubmit onClickParent={onClickParent} addSeller={addSeller} />}



            {!newImage && <Input type="file" onChange={onChangeFile} />}
            {newImage && <AutoUploadImage image={newImage} uploading={pendingFileUpload} />}
            <div className="text-center mt-2">
                <ButtonWithProgress
                    className="btn btn-primary"
                    onClick={onClickSave}
                    text={t("Save")}
                    pendingApiCall={pendingApiCall}
                    disabled={pendingApiCall || pendingFileUpload}
                />
            </div>

        </div>

    );
};

export default ProductSubmit;