import styled from "styled-components";

const sizes = {
    smallScreen: '377',
    mediumScreen: '480px'
};

export const devices = {
    smallScreen: `(max-width: ${sizes.smallScreen})`,
    mediumScreen: `(max-width: ${sizes.mediumScreen})`,
}


export const Wrapper = styled.div`
  display: flex;
  width: 100%;
  padding-bottom: 10px;
  @media ${devices.mediumScreen} {
    flex-direction: ${props => props.directionValue};
    justify-content: ${props => props.justifyValue}
  }
`;

export const Title = styled.div`
  width: fit-content;
  padding-right: 20px;
  font-size: 25px;
  display: flex;
  justify-content: center;
  align-items: center;
  
  @media ${devices.mediumScreen} {
    width: 100%;
    padding: 0;
  }
  
`;

export const Support = styled.div`
    width: fit-content;
    @media ${devices.mediumScreen} {
        width: 100%;
    }
`;

export const NameTitle = styled.div`
  font-size: 14px;
  color: gray;
  font-weight: bold;
  padding: 0 0 0 70px;
  text-align: initial;
  text-transform: uppercase;
  @media ${devices.mediumScreen} {
    padding: 0;
    text-align: center;    
  }
`;
export const Subtitle = styled.div`
  font-weight: bold;
  padding: 10px 0;
`;

export const Info = styled.div`
  width: 100%;
  text-align: initial;
  padding-left: 20px;
  @media ${devices.mediumScreen} {
        width: auto;
    }
`;
export const Contact = styled.div`
  width: 100%;
  display: inline-flex;
  justify-content: space-between;
`;
export const YellowMark = styled.div`
  width: 60px;
  background-color: #f8ce03;
  border-radius: 5px;
  margin-top: 10px;
  font-weight: bold;
  display: flex;
  justify-content: center;
  align-items: center;
`;
