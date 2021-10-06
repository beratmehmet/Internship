import defaultPicture from '../assets/profile.png';

import React from 'react';

const ProfileImageWithDefault = (props) => {
    const {image,tempimage} = props
    let imageSource = defaultPicture;
    if(image){
        imageSource='images/profile/'+image
    }
    return (
        
            <img
            src={tempimage||imageSource}
            alt='profile'
            {...props}
            onError={(event)=>{event.target.src=defaultPicture}}
            />

        
    );
};

export default ProfileImageWithDefault;