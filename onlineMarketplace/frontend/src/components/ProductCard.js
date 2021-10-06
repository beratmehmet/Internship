import React, { useEffect, useState } from 'react';
import { useTranslation } from 'react-i18next';
import { Link } from 'react-router-dom';
import { deleteProduct, getSellersWOPage, postProductAttachment, updateProduct } from '../api/apiCalls';
import ProductImageDefault from '../assets/default-product.jpg';
import Modal from '../components/Modal';
import { useHistory } from 'react-router';
import Input from './Input';
import SellerDropDown from './SellerDropDown';
import AutoUploadImage from './AutoUploadImage';
import { useApiProgress } from '../shared/ApiProgress';
import ButtonWithProgress from './ButtonWithProgress';
import { useSelector } from 'react-redux';

const ProductCard = (props) => {
    const { isAdmin } = useSelector((store) => ({ isAdmin: store.isAdmin }))
    const { seller } = props;
    const [product, setProduct] = useState({});
    const [inEditMode, setInEditMode] = useState(false);
    const [updatedName, setUpdatedName] = useState();
    const [updatedContent, setupdatedContent] = useState();
    const [updatedPrice, setupdatedPrice] = useState();
    const [updatedSellerId, setupdatedSellerId] = useState();
    const [updatedAttachmentId, setupdatedAttachmentId] = useState();
    const [validationErrors, setValidationErrors] = useState({});
    const { id, name, content, fileAttachmentVM, price } = product;
    const [page, setPage] = useState([])
    const [modalVisible, setModalVisible] = useState(false);
    const { t } = useTranslation();
    const history = useHistory();
    const [newImage, setNewImage] = useState('');
    let imageSource = ProductImageDefault;
    if (fileAttachmentVM) {
        imageSource = 'images/attachments/' + fileAttachmentVM.name;
    }

    useEffect(() => {
        setProduct(props.product)
    }, [props.product])

    useEffect(() => {
        setValidationErrors((previousValidationErrors) => ({
            ...previousValidationErrors,
            name: undefined
        }))
    }, [updatedName])
    useEffect(() => {
        setValidationErrors((previousValidationErrors) => ({
            ...previousValidationErrors,
            name: undefined
        }))
    }, [updatedContent])
    useEffect(() => {
        setValidationErrors((previousValidationErrors) => ({
            ...previousValidationErrors,
            name: undefined
        }))
    }, [updatedPrice])

    useEffect(() => {
        if (!inEditMode) {
            setUpdatedName(undefined);
            setupdatedAttachmentId(undefined);
            setupdatedContent(undefined);
            setupdatedSellerId(undefined);
            setupdatedPrice(undefined);
        } else {
            loadSellers();
            setUpdatedName(name);
            if(fileAttachmentVM){setupdatedAttachmentId(fileAttachmentVM.id);}
            setupdatedContent(content);
            setupdatedSellerId(seller.id);
            setupdatedPrice(price);
        }

    }, [inEditMode, name, content, fileAttachmentVM, price, seller])

    const onClickDelete = async () => {
        await deleteProduct(id);
        setModalVisible(false);
        history.push('/products');
    }

    const onClickCancel = () => {
        setModalVisible(false);
    }
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

    const loadSellers = async () => {
        try {
            const response = await getSellersWOPage();
            setPage(response.data);
        } catch (error) {
            setValidationErrors(error.response.data.validationErrors);
        }
    }

    const uploadFile = async (file) => {
        const attachment = new FormData();
        attachment.append('file', file);
        const response = await postProductAttachment(attachment);
        setupdatedAttachmentId(response.data.id)
    }

    const onClickSave = async () => {
        if (updatedSellerId === "" || updatedSellerId === null) {
            return;
        }
        const body = {
            name:updatedName,
            content:updatedContent,
            price:updatedPrice,
            sellerId:updatedSellerId,
            attachmentId:updatedAttachmentId
        }
        try {
            await updateProduct(id,body);
            if(body.attachmentId){
                imageSource = newImage;
            }
            setInEditMode(false);
            setNewImage('');
            window.location.reload();
        } catch (error) {
            if (error.response.data.validationErrors) {
                setValidationErrors(error.response.data.validationErrors);
            }
        }
    };

    const pendingApiCall = useApiProgress('post', '/api/1.0/products'+id, true);
    const pendingFileUpload = useApiProgress('post', '/api/1.0/product-attachments', true);
    const { name: nameError, image: imageError, price: priceError, content: contentError } = validationErrors;
    return (
        <div className="container">
            <div className="row">
                <div className="col-8">
                    <div className="card text-center">
                        <div className="card-header bg-white">
                            <img className="img-fluid height: auto" style={{ width: 300 }} src={imageSource} alt={name + "image"}></img>
                        </div>
                        <div className="card-body bg-light">
                        <h3>{name}</h3>
                           {isAdmin && <>
                            {!inEditMode &&
                                <>
                                    <button className="btn btn-success d-inline-flex"
                                        onClick={() => { setInEditMode(true) }}>
                                        <i className="material-icons">edit</i>
                                        {t('Edit')}
                                    </button>
                                    <button className="btn btn-danger d-inline-flex ms-2"
                                        onClick={() => { setModalVisible(true) }}>
                                        <i className="material-icons">directions_run</i>
                                        {t('Delete Product')}
                                    </button>
                                </>
                            }
                            {inEditMode &&
                                <div className="container">


                                    <Input label={t("Change Name")}
                                        defaultValue={name}
                                        onChange={(event) => {
                                            setUpdatedName(event.target.value);
                                        }}
                                        error={nameError}
                                    />
                                    <Input label={t("Change Content")}
                                        defaultValue={content}
                                        onChange={(event) => {
                                            setupdatedContent(event.target.value);
                                        }}
                                        error={contentError}
                                    />
                                    <Input label={t("Change Price")}
                                        defaultValue={price}
                                        onChange={(event) => {
                                            setupdatedPrice(event.target.value);
                                        }}
                                        error={priceError}
                                    />
                                    <div className="row">
                                        <span className="was-validated d-flex-inline">
                                            <select name="sellerId" value={seller.id} className="btn btn-primary" id="dropdown pre-scrollable" flip="true" onChange={(event) => {
                                                setupdatedSellerId(event.target.value);
                                            }} required>
                                                <option value="">{t('Choose Seller')}</option>
                                                {page.map(seller => (
                                                    <SellerDropDown key={seller.name} seller={seller} />
                                                ))}
                                            </select>

                                            <span className="invalid-feedback">
                                                Please choose a seller
                                            </span>
                                        </span>

                                    </div>

                                    {!newImage && <Input type="file" onChange={onChangeFile} error={imageError} />}
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

                            }
                            </>}
                            
                            

                        </div>
                        <div className="card-body bg-white">

                            <h5>{content}</h5>
                        </div>
                    </div>
                </div>
                <div className="col-2 mt-5">
                    <div className="card text-center mt-5">
                        <div className="card-header">
                            <h3>{price}TL</h3>
                        </div>
                        <div className="card-body">
                            <Link className="btn btn-primary" to={`/seller/${seller.id}`}>{seller.name}</Link>
                        </div>
                    </div>
                </div>
                <Modal
                    visible={modalVisible}
                    title={t('Delete Product')}
                    okButton={t('Delete Product')}
                    onClickCancel={onClickCancel}
                    onClickOk={onClickDelete}
                    message={
                        <div>
                            <div>
                                <strong>{t('Are you sure to delete this product?')}</strong>
                            </div>
                            <span></span>
                        </div>
                    }

                />
            </div>
        </div>
    );
};

export default ProductCard;