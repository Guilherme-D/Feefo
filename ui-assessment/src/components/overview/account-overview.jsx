import React from 'react';

import Upload from "../upload/upload";
import ContactInfo from "../contact-info/contact-info";
import Percentages from "../percentage/percentages";
import {AccountOverviewWrapper, SalesWrapper} from "./style";

export const AccountOverview = ({data}) => {
  console.log(data);

  return (

    <AccountOverviewWrapper>
        <ContactInfo data={data?.supportContact}/>
        <SalesWrapper>
            <Upload data={data?.salesOverview}/>
            <Percentages data={data?.salesOverview}/>
        </SalesWrapper>
    </AccountOverviewWrapper>
  )
}

export default AccountOverview;
