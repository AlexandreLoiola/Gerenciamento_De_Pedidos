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
    font-weight: 500;
    border: none;
    border-radius: 4px;
    box-shadow: 6px 6px 4px 4px rgba(0, 0, 0, 0.6);
    cursor: pointer;

  &:hover {
    background-color: #3D9F2A;
    transition: font-size .5s;
  }

  &:active {
    box-shadow: inset 0 3px 8px rgba(0, 0, 0, 0.6);
    transform: translateY(2px);
  }
`;
