import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IFundsTransferTransactionDetails, NewFundsTransferTransactionDetails } from '../funds-transfer-transaction-details.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IFundsTransferTransactionDetails for edit and NewFundsTransferTransactionDetailsFormGroupInput for create.
 */
type FundsTransferTransactionDetailsFormGroupInput =
  | IFundsTransferTransactionDetails
  | PartialWithRequiredKeyOf<NewFundsTransferTransactionDetails>;

type FundsTransferTransactionDetailsFormDefaults = Pick<NewFundsTransferTransactionDetails, 'id'>;

type FundsTransferTransactionDetailsFormGroupContent = {
  id: FormControl<IFundsTransferTransactionDetails['id'] | NewFundsTransferTransactionDetails['id']>;
  ftTrnxDetailsId: FormControl<IFundsTransferTransactionDetails['ftTrnxDetailsId']>;
  ftTrnxDetailsUlid: FormControl<IFundsTransferTransactionDetails['ftTrnxDetailsUlid']>;
  transactionId: FormControl<IFundsTransferTransactionDetails['transactionId']>;
  debitAccountNumber: FormControl<IFundsTransferTransactionDetails['debitAccountNumber']>;
  creditAccountNumber: FormControl<IFundsTransferTransactionDetails['creditAccountNumber']>;
  remitterName: FormControl<IFundsTransferTransactionDetails['remitterName']>;
  amount: FormControl<IFundsTransferTransactionDetails['amount']>;
  currency: FormControl<IFundsTransferTransactionDetails['currency']>;
  transactionType: FormControl<IFundsTransferTransactionDetails['transactionType']>;
  paymentDescription: FormControl<IFundsTransferTransactionDetails['paymentDescription']>;
  beneficiaryIFSC: FormControl<IFundsTransferTransactionDetails['beneficiaryIFSC']>;
  beneficiaryName: FormControl<IFundsTransferTransactionDetails['beneficiaryName']>;
  beneficiaryAddress: FormControl<IFundsTransferTransactionDetails['beneficiaryAddress']>;
  emailId: FormControl<IFundsTransferTransactionDetails['emailId']>;
  mobileNo: FormControl<IFundsTransferTransactionDetails['mobileNo']>;
  transactionRefNo: FormControl<IFundsTransferTransactionDetails['transactionRefNo']>;
  trnxResourceDataStatus: FormControl<IFundsTransferTransactionDetails['trnxResourceDataStatus']>;
  trnxMetaDataStatus: FormControl<IFundsTransferTransactionDetails['trnxMetaDataStatus']>;
  transactionDateTime: FormControl<IFundsTransferTransactionDetails['transactionDateTime']>;
  participantsettlement: FormControl<IFundsTransferTransactionDetails['participantsettlement']>;
  disbursement: FormControl<IFundsTransferTransactionDetails['disbursement']>;
  repayment: FormControl<IFundsTransferTransactionDetails['repayment']>;
};

export type FundsTransferTransactionDetailsFormGroup = FormGroup<FundsTransferTransactionDetailsFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class FundsTransferTransactionDetailsFormService {
  createFundsTransferTransactionDetailsFormGroup(
    fundsTransferTransactionDetails: FundsTransferTransactionDetailsFormGroupInput = { id: null },
  ): FundsTransferTransactionDetailsFormGroup {
    const fundsTransferTransactionDetailsRawValue = {
      ...this.getFormDefaults(),
      ...fundsTransferTransactionDetails,
    };
    return new FormGroup<FundsTransferTransactionDetailsFormGroupContent>({
      id: new FormControl(
        { value: fundsTransferTransactionDetailsRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      ftTrnxDetailsId: new FormControl(fundsTransferTransactionDetailsRawValue.ftTrnxDetailsId),
      ftTrnxDetailsUlid: new FormControl(fundsTransferTransactionDetailsRawValue.ftTrnxDetailsUlid),
      transactionId: new FormControl(fundsTransferTransactionDetailsRawValue.transactionId, {
        validators: [Validators.required],
      }),
      debitAccountNumber: new FormControl(fundsTransferTransactionDetailsRawValue.debitAccountNumber, {
        validators: [Validators.required],
      }),
      creditAccountNumber: new FormControl(fundsTransferTransactionDetailsRawValue.creditAccountNumber, {
        validators: [Validators.required],
      }),
      remitterName: new FormControl(fundsTransferTransactionDetailsRawValue.remitterName, {
        validators: [Validators.required],
      }),
      amount: new FormControl(fundsTransferTransactionDetailsRawValue.amount, {
        validators: [Validators.required],
      }),
      currency: new FormControl(fundsTransferTransactionDetailsRawValue.currency, {
        validators: [Validators.required],
      }),
      transactionType: new FormControl(fundsTransferTransactionDetailsRawValue.transactionType, {
        validators: [Validators.required],
      }),
      paymentDescription: new FormControl(fundsTransferTransactionDetailsRawValue.paymentDescription, {
        validators: [Validators.required],
      }),
      beneficiaryIFSC: new FormControl(fundsTransferTransactionDetailsRawValue.beneficiaryIFSC, {
        validators: [Validators.required],
      }),
      beneficiaryName: new FormControl(fundsTransferTransactionDetailsRawValue.beneficiaryName, {
        validators: [Validators.required],
      }),
      beneficiaryAddress: new FormControl(fundsTransferTransactionDetailsRawValue.beneficiaryAddress, {
        validators: [Validators.required],
      }),
      emailId: new FormControl(fundsTransferTransactionDetailsRawValue.emailId, {
        validators: [Validators.required],
      }),
      mobileNo: new FormControl(fundsTransferTransactionDetailsRawValue.mobileNo, {
        validators: [Validators.required],
      }),
      transactionRefNo: new FormControl(fundsTransferTransactionDetailsRawValue.transactionRefNo, {
        validators: [Validators.required],
      }),
      trnxResourceDataStatus: new FormControl(fundsTransferTransactionDetailsRawValue.trnxResourceDataStatus, {
        validators: [Validators.required],
      }),
      trnxMetaDataStatus: new FormControl(fundsTransferTransactionDetailsRawValue.trnxMetaDataStatus, {
        validators: [Validators.required],
      }),
      transactionDateTime: new FormControl(fundsTransferTransactionDetailsRawValue.transactionDateTime, {
        validators: [Validators.required],
      }),
      participantsettlement: new FormControl(fundsTransferTransactionDetailsRawValue.participantsettlement),
      disbursement: new FormControl(fundsTransferTransactionDetailsRawValue.disbursement),
      repayment: new FormControl(fundsTransferTransactionDetailsRawValue.repayment),
    });
  }

  getFundsTransferTransactionDetails(
    form: FundsTransferTransactionDetailsFormGroup,
  ): IFundsTransferTransactionDetails | NewFundsTransferTransactionDetails {
    return form.getRawValue() as IFundsTransferTransactionDetails | NewFundsTransferTransactionDetails;
  }

  resetForm(
    form: FundsTransferTransactionDetailsFormGroup,
    fundsTransferTransactionDetails: FundsTransferTransactionDetailsFormGroupInput,
  ): void {
    const fundsTransferTransactionDetailsRawValue = { ...this.getFormDefaults(), ...fundsTransferTransactionDetails };
    form.reset(
      {
        ...fundsTransferTransactionDetailsRawValue,
        id: { value: fundsTransferTransactionDetailsRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): FundsTransferTransactionDetailsFormDefaults {
    return {
      id: null,
    };
  }
}
