import React from 'react';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {faUpload} from '@fortawesome/free-solid-svg-icons';

import {SalesUploadWrapper, Tooltip, UploadValues,} from "./style";

export const Upload = ({data}) => {
    var successfulUploads = data? data?.successfulUploads : 0;
    var uploads = data? data?.uploads : 0;

  return (

    <SalesUploadWrapper>
        <UploadValues>
            <div><FontAwesomeIcon icon={faUpload} color={"#3EAFEAFF"} />  Sales</div>
            <Tooltip>i</Tooltip>
        </UploadValues>
        <div>
            You had <b>{successfulUploads} uploads</b> and <b>{uploads} lines</b> added
        </div>
    </SalesUploadWrapper>
  )
}

export default Upload;
