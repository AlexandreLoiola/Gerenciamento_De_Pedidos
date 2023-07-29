import styled from 'styled-components';

export const StyledTable = styled.table`
  border-collapse: collapse;
  width: 70%;
  margin: 5% 15%; 
  color: #fff;
`;

export const StyledTh = styled.th`
  border-bottom: 1px solid #ddd;
  padding: 8px;
  text-align: left;
`;

export const StyledTd = styled.td`
  border-bottom: 2px solid green;
  border-top: 2px solid green;
  padding: 8px;
`;

export const StyledTr = styled.tr`
  /* background-color: #fff;
  &:nth-child(even) {
    background-color: #f2f2f2;
  } */
`;

export const StyledAlterButton = styled.button`
    display: inline;
    width: 5vw;
    justify-content: center;
    padding: 10px 1px;
    margin-left: 20px;
    background-color: #317B22;
    color: #fff;
    font-size: 14px;
    border: none;
    border-radius: 4px;
    box-shadow: 4px 4px 4px 4px rgba(0, 0, 0, 0.2);
    cursor: pointer;

  &:hover {
    background-color: #3D9F2A;
    transition: font-size 3s;
  } 
`;

export const StyledDeleteButton = styled.button`
    display: inline;
    width: 5vw;
    justify-content: center;
    padding: 10px 1px;
    margin-left: 20px;
    background-color: #317B22;
    color: #fff;
    font-size: 14px;
    border: none;
    border-radius: 4px;
    box-shadow: 4px 4px 4px 4px rgba(0, 0, 0, 0.2);
    cursor: pointer;

  &:hover {
    background-color: #3D9F2A;
    transition: font-size 3s;
  } 
`;

