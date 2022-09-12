import axios from "axios";
import { useEffect, useState } from "react";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import { Sale } from "../../models/sale";
import { BASE_URL } from "../../utils/request";
import NotificationButton from '../NotificationButton'
import './styles.css'

function SalesCard() {

    const [minDate, setMinDate] = useState(new Date());
    const [maxDate, setMaxDate] = useState(new Date());

    const [sales, setSales] = useState<Sale[]>([]);

    useEffect(() => {

        const dmin = minDate.toISOString().slice(0, 10);
        const dmax = minDate.toISOString().slice(0, 10);


        axios.get(`${BASE_URL}/sales?minDate=${dmin}&maxDate=${dmax}`)
            .then(response => {
                setSales(response.data.content)
            });
    }, [minDate, maxDate]);

    return (
        <div className="mtfuncionario-card">
            <h2 className="mtfuncionario-sales-title">Vendas</h2>
            <div>
                <div className="mtfuncionario-form-control-container">
                    <DatePicker
                        selected={minDate}
                        onChange={(date: Date) => setMinDate(date)}
                        className="mtfuncionario-form-control"
                        dateFormat="dd/MM/yyyy"
                    />
                </div>
                <div className="mtfuncionario-form-control-container">
                    <DatePicker
                        selected={maxDate}
                        onChange={(date: Date) => setMaxDate(date)}
                        className="mtfuncionario-form-control"
                        dateFormat="dd/MM/yyyy"
                    />
                </div>
            </div>

            <div>
                <table className="mtfuncionario-sales-table">
                    <thead>
                        <tr>
                            <th className="show992">ID</th>
                            <th className="show576">Data</th>
                            <th>Vendedor</th>
                            <th className="show992">Visitas</th>
                            <th className="show992">Vendas</th>
                            <th>Total</th>
                            <th>Notificar</th>
                        </tr>
                    </thead>
                    <tbody>
                        {sales.map(x => {
                            return (
                                <tr key={x.id}>
                                    <td className="show992">{x.id}</td>
                                    <td className="show576">{new Date(x.date).toLocaleString()}</td>
                                    <td>{x.sellerName}</td>
                                    <td className="show992">{x.visited}</td>
                                    <td className="show992">{x.deals}</td>
                                    <td>{x.amount.toFixed(2)}</td>
                                    <td>
                                        <div className="mtfuncionario-red-btn-container">
                                            <NotificationButton saleId={x.id} />
                                        </div>
                                    </td>
                                </tr>
                            )
                        })}
                    </tbody>

                </table>
            </div>

        </div>
    )
}

export default SalesCard
