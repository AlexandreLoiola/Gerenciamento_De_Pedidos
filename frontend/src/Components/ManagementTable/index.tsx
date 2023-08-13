import React, { useEffect } from "react";
import { Button, Table } from "react-bootstrap";
import TableToggle from "./TableToggle";
import { PencilIcon, ThrashIcon } from "./styles";
import { useNavigate } from "react-router-dom";

interface IDataType {
  [key: string]: any;
}

interface IProps {
  columnTitles: string[];
  data: IDataType[];
  objectKeys: string[];
  redirectToUpdateForm: string;
  changePageToPagination: number;

  handleDelete: (index: any) => void;
  handleStatusUpdate: (data: any) => void;
}

const ManagementTable: React.FC<IProps> = ({
  columnTitles,
  data,
  objectKeys,
  redirectToUpdateForm,
  changePageToPagination,
  handleDelete,
  handleStatusUpdate,
}) => {
  const navigate = useNavigate();

  const getNestedValue = (obj: any, path: string) => {
    return path.split('.').reduce((acc, key) => acc && acc[key], obj);
  }

  return (
    <Table responsive striped bordered hover>
      <thead>
        <tr>
          {columnTitles.map((column) => (
            <th key={column}>{column}</th>
          ))}
        </tr>
      </thead>
      <tbody>
        {data
          .slice((changePageToPagination - 1) * 5, changePageToPagination * 5)
          .map((data, index) => (
            <tr key={index}>
              <td>{(changePageToPagination - 1) * 5 + index + 1}</td>
              {objectKeys.map((key) => (
                <td key={key}>
                  {typeof getNestedValue(data, key) === "boolean" ? (
                    <TableToggle
                      initialValue={getNestedValue(data, key)}
                      onToggle={() => {
                        handleStatusUpdate({ ...data, [key]: !getNestedValue(data, key) });
                      }}
                    />
                  ) : (
                    getNestedValue(data, key)
                  )}
                  
                </td>
              ))}
              <td>
                <Button
                  variant="warning"
                  style={{ margin: "0 6px" }}
                  onClick={() => {
                    navigate(redirectToUpdateForm, { state: data });
                  }}
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
