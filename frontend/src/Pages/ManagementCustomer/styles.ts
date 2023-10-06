import styled from "styled-components";

export const FormContainer = styled.div`
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;
  margin: auto;
`;

export const SearchFormInput = styled.div`
    display: flex;
    justify-content: center;
    align-items: center;
    margin-bottom: 20px;

    input {
      z-index: 2;
      height: 51px;
    }

  button {
    margin-left: -50px;
    margin-top: 1px;
    margin-right: 28px;
    box-shadow: none;
    z-index: 1;
    width: 40%;; 
  }
`;


export const FormInput = styled.input`
  display: flex;
  margin: 0 25px;
  width: 100%;
  padding: 0 30px;
  height: 60px;
  border-radius: 15px;
  margin-bottom: 20px;
  border: none;
  outline: none;
  box-sizing: border-box;
  cursor: text;

  font-weight: regular;
  font-size: 18px;

  &:focus {
    border: 3px solid #3d9f2a;
  }
`;

export const FormRow = styled.div`
  display: flex;
  justify-content: center;
  gap: 50px;
  align-items: center;
  margin-bottom: 20px; 
`;

export const ButtonContainer = styled.div`
  display: flex;
  justify-content: center;
  margin-top: 30px;

  button {
    margin: 0 50px;
  }
`;