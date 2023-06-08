import styled from 'styled-components';

export const Button = styled.button`
    display: flex;  
    justify-content: center;
    align-items: center;
    width: 380px;
    height: 100px;
    margin: auto;
    margin-top: 30px;
    background-color: #317B22;
    color: #fff;
    font-size: 22px;
    font-weight: normal;
    border: none;
    border-radius: 4px;
    box-shadow: 6px 6px 4px 4px rgba(0, 0, 0, 0.6);
    cursor: pointer;

  &:hover {
    background-color: #3D9F2A;
    font-size: 26px;
    transition: font-size .5s;
  }
`;
