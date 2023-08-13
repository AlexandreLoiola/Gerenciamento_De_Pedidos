import Form from "react-bootstrap/Form";
import {StyledFormControlReadonly, StyledFormLabel} from './styles';

interface IProps {
  label: string;
  readonlyText: string;
}

const ReadonlyForm: React.FC<IProps> = ({ label, readonlyText }) => {
  return (
    <Form>
      <Form.Group className="mb-3" controlId="formPlaintextEmail">
        <StyledFormLabel>{label}</StyledFormLabel>
        <StyledFormControlReadonly plaintext readOnly defaultValue={readonlyText} />
      </Form.Group>
    </Form>
  );
};

export default ReadonlyForm;
