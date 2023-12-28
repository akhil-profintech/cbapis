import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IFundsTransfer, NewFundsTransfer } from '../funds-transfer.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IFundsTransfer for edit and NewFundsTransferFormGroupInput for create.
 */
type FundsTransferFormGroupInput = IFundsTransfer | PartialWithRequiredKeyOf<NewFundsTransfer>;

type FundsTransferFormDefaults = Pick<NewFundsTransfer, 'id'>;

type FundsTransferFormGroupContent = {
  id: FormControl<IFundsTransfer['id'] | NewFundsTransfer['id']>;
  fundsTransferId: FormControl<IFundsTransfer['fundsTransferId']>;
  fundsTransferRefNo: FormControl<IFundsTransfer['fundsTransferRefNo']>;
  finReqId: FormControl<IFundsTransfer['finReqId']>;
  subGrpId: FormControl<IFundsTransfer['subGrpId']>;
  transactionId: FormControl<IFundsTransfer['transactionId']>;
  debitAccountNumber: FormControl<IFundsTransfer['debitAccountNumber']>;
  creditAccountNumber: FormControl<IFundsTransfer['creditAccountNumber']>;
  remitterName: FormControl<IFundsTransfer['remitterName']>;
  amount: FormControl<IFundsTransfer['amount']>;
  currency: FormControl<IFundsTransfer['currency']>;
  transactionType: FormControl<IFundsTransfer['transactionType']>;
  paymentDescription: FormControl<IFundsTransfer['paymentDescription']>;
  beneficiaryIFSC: FormControl<IFundsTransfer['beneficiaryIFSC']>;
  beneficiaryName: FormControl<IFundsTransfer['beneficiaryName']>;
  beneficiaryAddress: FormControl<IFundsTransfer['beneficiaryAddress']>;
  emailId: FormControl<IFundsTransfer['emailId']>;
  mobileNo: FormControl<IFundsTransfer['mobileNo']>;
  messageType: FormControl<IFundsTransfer['messageType']>;
  transactionDateTime: FormControl<IFundsTransfer['transactionDateTime']>;
  transactionRefNo: FormControl<IFundsTransfer['transactionRefNo']>;
  trnxMetaDataStatus: FormControl<IFundsTransfer['trnxMetaDataStatus']>;
  trnxMetaDataMessage: FormControl<IFundsTransfer['trnxMetaDataMessage']>;
  trnxMetaDataVersion: FormControl<IFundsTransfer['trnxMetaDataVersion']>;
  trnxMetaDataTime: FormControl<IFundsTransfer['trnxMetaDataTime']>;
  trnxResourceDataBeneficiaryName: FormControl<IFundsTransfer['trnxResourceDataBeneficiaryName']>;
  trnxResourceDataTransactionId: FormControl<IFundsTransfer['trnxResourceDataTransactionId']>;
  trnxResourceDataStatus: FormControl<IFundsTransfer['trnxResourceDataStatus']>;
  fundsTransferStatus: FormControl<IFundsTransfer['fundsTransferStatus']>;
  lastupdatedDateTime: FormControl<IFundsTransfer['lastupdatedDateTime']>;
  lastUpdatedBy: FormControl<IFundsTransfer['lastUpdatedBy']>;
  tradeEntity: FormControl<IFundsTransfer['tradeEntity']>;
};

export type FundsTransferFormGroup = FormGroup<FundsTransferFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class FundsTransferFormService {
  createFundsTransferFormGroup(fundsTransfer: FundsTransferFormGroupInput = { id: null }): FundsTransferFormGroup {
    const fundsTransferRawValue = {
      ...this.getFormDefaults(),
      ...fundsTransfer,
    };
    return new FormGroup<FundsTransferFormGroupContent>({
      id: new FormControl(
        { value: fundsTransferRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      fundsTransferId: new FormControl(fundsTransferRawValue.fundsTransferId),
      fundsTransferRefNo: new FormControl(fundsTransferRawValue.fundsTransferRefNo),
      finReqId: new FormControl(fundsTransferRawValue.finReqId),
      subGrpId: new FormControl(fundsTransferRawValue.subGrpId),
      transactionId: new FormControl(fundsTransferRawValue.transactionId, {
        validators: [Validators.required],
      }),
      debitAccountNumber: new FormControl(fundsTransferRawValue.debitAccountNumber),
      creditAccountNumber: new FormControl(fundsTransferRawValue.creditAccountNumber),
      remitterName: new FormControl(fundsTransferRawValue.remitterName),
      amount: new FormControl(fundsTransferRawValue.amount),
      currency: new FormControl(fundsTransferRawValue.currency),
      transactionType: new FormControl(fundsTransferRawValue.transactionType),
      paymentDescription: new FormControl(fundsTransferRawValue.paymentDescription),
      beneficiaryIFSC: new FormControl(fundsTransferRawValue.beneficiaryIFSC),
      beneficiaryName: new FormControl(fundsTransferRawValue.beneficiaryName),
      beneficiaryAddress: new FormControl(fundsTransferRawValue.beneficiaryAddress),
      emailId: new FormControl(fundsTransferRawValue.emailId),
      mobileNo: new FormControl(fundsTransferRawValue.mobileNo),
      messageType: new FormControl(fundsTransferRawValue.messageType),
      transactionDateTime: new FormControl(fundsTransferRawValue.transactionDateTime),
      transactionRefNo: new FormControl(fundsTransferRawValue.transactionRefNo),
      trnxMetaDataStatus: new FormControl(fundsTransferRawValue.trnxMetaDataStatus),
      trnxMetaDataMessage: new FormControl(fundsTransferRawValue.trnxMetaDataMessage),
      trnxMetaDataVersion: new FormControl(fundsTransferRawValue.trnxMetaDataVersion),
      trnxMetaDataTime: new FormControl(fundsTransferRawValue.trnxMetaDataTime),
      trnxResourceDataBeneficiaryName: new FormControl(fundsTransferRawValue.trnxResourceDataBeneficiaryName),
      trnxResourceDataTransactionId: new FormControl(fundsTransferRawValue.trnxResourceDataTransactionId),
      trnxResourceDataStatus: new FormControl(fundsTransferRawValue.trnxResourceDataStatus),
      fundsTransferStatus: new FormControl(fundsTransferRawValue.fundsTransferStatus),
      lastupdatedDateTime: new FormControl(fundsTransferRawValue.lastupdatedDateTime, {
        validators: [Validators.required],
      }),
      lastUpdatedBy: new FormControl(fundsTransferRawValue.lastUpdatedBy, {
        validators: [Validators.required],
      }),
      tradeEntity: new FormControl(fundsTransferRawValue.tradeEntity),
    });
  }

  getFundsTransfer(form: FundsTransferFormGroup): IFundsTransfer | NewFundsTransfer {
    return form.getRawValue() as IFundsTransfer | NewFundsTransfer;
  }

  resetForm(form: FundsTransferFormGroup, fundsTransfer: FundsTransferFormGroupInput): void {
    const fundsTransferRawValue = { ...this.getFormDefaults(), ...fundsTransfer };
    form.reset(
      {
        ...fundsTransferRawValue,
        id: { value: fundsTransferRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): FundsTransferFormDefaults {
    return {
      id: null,
    };
  }
}
