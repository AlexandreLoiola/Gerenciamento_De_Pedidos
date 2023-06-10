import styled from 'styled-components';

export const Button = styled.button`
    display: flex;
    width: 280px;
    height: 50px;
    margin: auto;
    margin-top: 30px;
    justify-content: center;
    padding: 15px 1px;
    background-color: #317B22;
    color: #fff;
    font-size: 16px;
    border: none;
    border-radius: 4px;
    box-shadow: 4px 4px 4px 4px rgba(0, 0, 0, 0.6);
    cursor: pointer;

  &:hover {
    background-color: #3D9F2A;
    font-size: 18px;
    transition: font-size .5s;
  }
`;
