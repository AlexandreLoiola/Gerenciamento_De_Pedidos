import React from "react";
import { Button, Table } from "react-bootstrap";
import TableToggle from "./TableToggle";
import { PencilIcon, ThrashIcon } from "./styles";
import { useNavigate } from 'react-router-dom';

interface IDataType {
  [key: string]: any;
}

interface IProps {
  columnTitles: string[];
  data: IDataType[];
  objectKeys: string[];
  redirectToUpdateForm: string;
  changePageToPagination: number;

  handleDelete: (index: number) => void;
  handleSatusUpdate: (data: any) => void;
}

const ManagementTable: React.FC<IProps> = ({
  columnTitles,
  data,
  objectKeys,
  redirectToUpdateForm,
  changePageToPagination,
  handleDelete,
  handleSatusUpdate,
}) => {

  const navigate = useNavigate();

  return (
    <Table striped bordered hover>
      <thead>
        <tr>
          {columnTitles.map((column) => (
            <th key={column}>{column}</th>
          ))}
        </tr>
      </thead>
      <tbody>
        {data.slice((changePageToPagination-1)*5, changePageToPagination*5).map((data, index) => (
          <tr key={index}>
            <td>{((changePageToPagination-1)*5) + index + 1}</td>
            {objectKeys.map((key) => (
              <td key={key}>
                {typeof data[key] === "boolean" ? (
                  <TableToggle
                    onToggle={() => {
                      const updatedData = { ...data, [key]: !data[key] };
                      handleSatusUpdate(updatedData);
                    }}
                    initialValue={data[key]}
                  />
                ) : (
                  data[key]
                )}
              </td>
            ))}
            <td>
              <Button
                variant="warning"
                style={{ margin: "0 6px" }}
                onClick={() => navigate(redirectToUpdateForm)}
              >
                <PencilIcon />
              </Button>
              <Button
                variant="danger"
                style={{ margin: "0 6px" }}
                onClick={() => handleDelete(index)}
              >
                <ThrashIcon />
              </Button>
            </td>
          </tr>
        ))}
      </tbody>
    </Table>
  );
};

export default ManagementTable;
