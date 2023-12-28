import { IBeneValidation } from 'app/entities/bene-validation/bene-validation.model';
import { IInstaAlert } from 'app/entities/insta-alert/insta-alert.model';
import { IFundsTransfer } from 'app/entities/funds-transfer/funds-transfer.model';
import { IUpdateVA } from 'app/entities/update-va/update-va.model';
import { IVANumber } from 'app/entities/va-number/va-number.model';

export interface ITradeEntity {
  id: number;
  entityId?: number | null;
  entityName?: string | null;
  entityDesc?: string | null;
  entityGST?: string | null;
  beneValidations?: Pick<IBeneValidation, 'id'>[] | null;
  instaAlerts?: Pick<IInstaAlert, 'id'>[] | null;
  fundsTransfers?: Pick<IFundsTransfer, 'id'>[] | null;
  updateVAS?: Pick<IUpdateVA, 'id'>[] | null;
  vANumbers?: Pick<IVANumber, 'id'>[] | null;
}

export type NewTradeEntity = Omit<ITradeEntity, 'id'> & { id: null };
