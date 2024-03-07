import dayjs from 'dayjs/esm';
import { IRequestOffer } from 'app/entities/request-offer/request-offer.model';
import { ITrade } from 'app/entities/trade/trade.model';
import { IPlacedOffer } from 'app/entities/placed-offer/placed-offer.model';
import { IAcceptedOffer } from 'app/entities/accepted-offer/accepted-offer.model';
import { IDisbursement } from 'app/entities/disbursement/disbursement.model';
import { IDocDetails } from 'app/entities/doc-details/doc-details.model';
import { IRepayment } from 'app/entities/repayment/repayment.model';
import { ISettlement } from 'app/entities/settlement/settlement.model';
import { ICBCREProcess } from 'app/entities/cbcre-process/cbcre-process.model';
import { IAnchorTrader } from 'app/entities/anchor-trader/anchor-trader.model';

export interface IFinanceRequest {
  id: number;
  financeRequestId?: number | null;
  financeRequestUlidId?: string | null;
  financeRequestRefNo?: string | null;
  tradeChannelId?: string | null;
  requestAmount?: string | null;
  requestDate?: dayjs.Dayjs | null;
  currency?: string | null;
  requestStatus?: string | null;
  dueDate?: dayjs.Dayjs | null;
  gstConsent?: boolean | null;
  requestOffers?: Pick<IRequestOffer, 'id'>[] | null;
  trades?: Pick<ITrade, 'id'>[] | null;
  placedOffers?: Pick<IPlacedOffer, 'id'>[] | null;
  acceptedOffers?: Pick<IAcceptedOffer, 'id'>[] | null;
  disbursements?: Pick<IDisbursement, 'id'>[] | null;
  docDetails?: Pick<IDocDetails, 'id'>[] | null;
  repayments?: Pick<IRepayment, 'id'>[] | null;
  settlements?: Pick<ISettlement, 'id'>[] | null;
  cBCREProcesses?: Pick<ICBCREProcess, 'id'>[] | null;
  anchortrader?: Pick<IAnchorTrader, 'id' | 'atId'> | null;
}

export type NewFinanceRequest = Omit<IFinanceRequest, 'id'> & { id: null };
