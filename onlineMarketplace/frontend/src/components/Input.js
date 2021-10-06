import React from 'react';



const Input = (props) => {
    const {label, error, name, onChange, type, defaultValue, value } = props;
    let className = 'form-control mt-2';

    if (type === 'file') {
        className += '-file';
    }


    if (error !== undefined) {
        className += ' is-invalid';
    }
    return (
        <div className="form-group">
            <label>{label}</label>
            <input className={className}
                name={name}
                onChange={onChange}
                type={type}
                value={value}
                defaultValue={defaultValue}
            />
            <div className="invalid-feedback">
                {error}
            </div>
        </div>
    );
}
export default Input;