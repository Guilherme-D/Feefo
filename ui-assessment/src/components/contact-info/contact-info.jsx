import React from 'react';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {faEnvelope} from '@fortawesome/free-solid-svg-icons';

import {Contact, Info, NameTitle, Subtitle, Support, Title, Wrapper, YellowMark} from "./style";

export const ContactInfo = ({data}) => {
  const phone = data?.phone? data.phone : ''
  const email = data?.email? data?.email : ''
  const name = data?.name? data?.name : '-'
  const initial = name[0]

  return (
    <Wrapper directionValue="column">
        <Title>Account Overview</Title>
        <Support>
            <NameTitle>YOUR FEEFO SUPPORT CONTACT</NameTitle>
            <Wrapper justifyValue="center" >
                <YellowMark>{initial}</YellowMark>
                <Info>
                    <Subtitle>{name}</Subtitle>
                    <Contact>
                        <span><FontAwesomeIcon icon={faEnvelope} color={"gray"} /> {email}</span>
                        <span>{phone}</span>
                    </Contact>
                </Info>
            </Wrapper>
        </Support>
    </Wrapper>
  )
}

export default ContactInfo;
