import styled from "styled-components";

export const ProductCardContainer = styled.div`
    display: flex;
    position: relative;
    margin: 80px auto;
    height: 200px;
    width: 800px;
    background-color: rgba(0, 0, 0, .85);
    color: white;
    padding: 20px;
    display: flex;
    align-items: center;
    border: 3px solid #317B22;
`;

export const LeftSide = styled.div`
  flex: 1;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  height: 100%;
  border-right: 3px solid white;
  width: 66% !important;
  margin: auto;
`;

export const RightSide = styled.div`
  flex: 1;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100%;
`;

export const Title = styled.h2`
  margin: 0;
  margin-bottom: 15px;
`;

export const Description = styled.p`
  margin: 0;
`;

export const Price = styled.span`
  font-size: 24px;
  font-weight: bold;
`;

export const BuyButton = styled.button`
  margin-top: 10px;
  padding: 8px 16px;
  background-color: #317B22;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;

  &:hover {
    background-color: #3D9F2A;
    font-weight: bold;
    transition: font-size .5s;
  }   
`;

export const QuantityInput = styled.input`
  width: 60px;
  height: 30px;
  padding: 0 10px;
  font-size: 14px;
  border: 1px solid #ccc;
  border-radius: 4px;
`;