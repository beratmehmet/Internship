import React from 'react';
import './UploadImage.css'

const AutoUploadImage = ({ image, uploading }) => {
    return (
        <div style={{position:'relative'}}>
             <img className="img-fluid height: auto" style={{width:300}} src={image} alt="product-attachment"/>
              <div className="overlay" style={{opacity: uploading ? 1 : 0}}>
                <div className="d-flex justify-content-center h-100">
                    <div className="spinner-border text-light m-auto">
                    <span className="sr-only"></span>
                </div>
        </div>
              </div>
        </div>
    );
};

export default AutoUploadImage;