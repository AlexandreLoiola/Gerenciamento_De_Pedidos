import styled from "styled-components";
import { Form, Button } from "react-bootstrap";
import { HiMagnifyingGlass } from "react-icons/hi2";
import { BsEyeFill, BsEyeSlashFill } from "react-icons/bs";

export const StyledFormGroup = styled(Form.Group)`
  margin-bottom: 1.5rem;
`;

export const StyledFormLabel = styled(Form.Label)`
  font-size: 1.2rem;
  color: #333;
`;

export const StyledFormControl = styled(Form.Control)`
  font-size: 1rem;
  background-color: white;
  padding: 0.5rem 1rem;
  border: 1px solid lightgray;
  width: 500px;
`;

export const StyledFormText = styled(Form.Text)`
  font-size: 0.8rem;
  color: #777;
`;

export const StyledFormControlReadonly = styled(Form.Control)`
  border: 1px solid #ccc;
  font-size: 1rem;
  background-color: #d3d3d3;
  width: 164px;
  padding: 0.5rem 1rem;
  cursor: not-allowed;

  &:hover {
    background-color: #a9a9a9;
  }
`;

export const FormContainer = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  height: 35vh;
`;

export const MagnifyingGlassIcon = styled(HiMagnifyingGlass)`
  color: white;
  font-size: 26px;
  font-weight: 700;
`;

export const EyeCloseIcon = styled(BsEyeFill)`
  color: #777;
  font-size: 20px;
  font-weight: 700;
`;
export const EyeOpenIcon = styled(BsEyeSlashFill)`
  color: #777;
  font-size: 20px;
  font-weight: 700;
`;

export const StyledButton = styled(Button)`
  display: flex;
  width: 280px;
  height: 50px;
  justify-content: center;
  margin: auto;
  padding: 12px 1px;
  font-size: 16px;
  font-weight: 500;
  margin-top: 40px;
  background-color: #31a17f;
  border: none;
  border-radius: 4px;
  box-shadow: 6px 6px 8px 2px rgba(0, 0, 0, 0.25);

  &:hover {
    background-color: #2c8a6c;
    transition: font-size 3s;
  }

  &:active {
    box-shadow: inset 0 3px 8px rgba(0, 0, 0, 0.6);
    transform: translateY(2px);
  }
`;
