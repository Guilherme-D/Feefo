import React from 'react';
import {Lines, PercentagesWrapper, PercentageValues, SalesPercentages} from "./style";

export const Percentages = ({data}) => {

    var successfulUploads = data? data?.successfulUploads : 0;
    var uploads = data? data?.uploads : 0;

    var linesSaved = data? data?.linesSaved : 0
    var linesAttempted = data? data?.linesAttempted : 0

    var successUploadPercentage = uploads === 0 ? 0 : (successfulUploads/uploads)*100
    var linesSavedPercentage = linesAttempted === 0 ? 0 : (linesSaved/linesAttempted)*100

  return (

    <div>
       <Lines />
        <SalesPercentages>
            <PercentagesWrapper>
                <PercentageValues>{successUploadPercentage} %</PercentageValues>
                <div>UPLOAD SUCCESS</div>
            </PercentagesWrapper>
            <Lines />
            <PercentagesWrapper>
                <PercentageValues>{linesSavedPercentage} %</PercentageValues>
                <div>LINES SAVED</div>
            </PercentagesWrapper>
        </SalesPercentages>
    </div>

  )
}

export default Percentages;
