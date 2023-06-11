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
  border-radius: 15px;
  margin-bottom: 20px;
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
  display: grid;
  grid-template-columns: repeat(2, 1fr);
`;

export const ButtonContainer = styled.div`
  display: flex;
  justify-content: center;
  margin-top: 50px;

  button {
    margin: 0 50px;
  }
`;
