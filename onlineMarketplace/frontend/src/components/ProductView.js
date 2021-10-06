import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import { useTranslation } from 'react-i18next';
import { addFav, deleteFav, deleteProduct } from '../api/apiCalls';
import Modal from './Modal';
import { useApiProgress } from '../shared/ApiProgress'
import ProductImageDefault from '../assets/default-product.jpg';
import { useSelector } from 'react-redux';

const ProductView = (props) => {
    const {username: loggedInUsername, isAdmin} = useSelector((store)=>({username:store.username, isAdmin:store.isAdmin}))
    const [modalVisible, setModalVisible] = useState(false);
    const { product, onDeleteProduct } = props;
    const [liked,setLiked]=useState(props.liked)
    const { content, name, fileAttachmentVM, id } = product;
    let image = ProductImageDefault;
    const [favClassName,setFavClassName]=useState(liked ? 'favorite' :'favorite_border');
   

    const { t } = useTranslation();

    const pendingApiCall = useApiProgress('delete', `/api/1.0/products/${id}`);

    if (fileAttachmentVM) {
        image = 'images/attachments/' + fileAttachmentVM.name;
    }

    

    const onClickDelete = async () => {
        await deleteProduct(id);
        onDeleteProduct(id);
    }

    const onClickFav = async () =>{
        
        if(liked){
            await deleteFav(loggedInUsername,id);
            setFavClassName('favorite_border')
            setLiked(false);
        }else{
            await addFav(loggedInUsername,id);
            setFavClassName('favorite');
            setLiked(true)
        }
    }

    const onClickCancel = () => {
        setModalVisible(false);
    }
    

    return (
        <>
            <div className="card p-1">
                <div className="d-flex">
                    {fileAttachmentVM && (
                        <div>
                            {fileAttachmentVM.fileType.startsWith('image') && (
                                <img className="img-fluid height: auto" style={{ width: 50 }} src={image} alt={content} />
                            )}
                            {!fileAttachmentVM.fileType.startsWith('image') && (
                                <strong>{t('Product has unsupported type')}</strong>
                            )}

                        </div>
                    )}
                    {!fileAttachmentVM &&
                        <div>
                            <img className="img-fluid height: auto" style={{ width: 50 }} src={image} alt={content} />
                        </div>
                    }
                    <div className="flex-fill m-auto ps-3">
                        <Link to={`/product/${id}`}>
                            <h6 className="d-inline">
                                {name}
                            </h6>
                        </Link>
                    </div>

                    {isAdmin && <button className="btn btn-delete-link btn-sm"
                        onClick={() => { setModalVisible(true); }}
                    >
                        <i className="material-icons">
                            delete_outline
                        </i>
                    </button>  
                    }
                    <button className="btn btn-delete-link btn-sm" onClick={onClickFav}>
                    <span className="material-icons">
                        {favClassName}
                    </span>
                    </button>

                </div>
                <div className="ps-5"><div className="ps-4">{content}</div></div>
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
                        <span>{content}</span>
                    </div>
                }
                pendingApiCall={pendingApiCall}
            />

        </>

    );
};

export default ProductView;