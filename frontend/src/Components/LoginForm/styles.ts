import styled from "styled-components";

export const FormContainer = styled.div`
    display: flex;
    justify-content: center;
    flex-direction: column;
`;

export const FormInput = styled.input`
    display: flex;
    margin: 0 auto;
    width: 450px;
    padding: 0 30px;
    height: 60px;
    border-radius: 50px;
    margin-bottom: 20px;
    outline: none;
    box-sizing: border-box;
    cursor: text;

    font-weight: regular;
    font-size: 18px;

    &:focus {
    border: 3px solid #3D9F2A;
  }
`;

export const SubmitButton = styled.button`
    display: flex;
    margin: 50px auto;
    padding: 15px 80px;
    background-color: #317B22;
    color: #fff;
    font-size: 16px;
    border: none;
    border-radius: 4px;
    cursor: pointer;

  &:hover {
    background-color: #3D9F2A;
  }
`;