import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IFTTransactionDetails, NewFTTransactionDetails } from '../ft-transaction-details.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IFTTransactionDetails for edit and NewFTTransactionDetailsFormGroupInput for create.
 */
type FTTransactionDetailsFormGroupInput = IFTTransactionDetails | PartialWithRequiredKeyOf<NewFTTransactionDetails>;

type FTTransactionDetailsFormDefaults = Pick<NewFTTransactionDetails, 'id'>;

type FTTransactionDetailsFormGroupContent = {
  id: FormControl<IFTTransactionDetails['id'] | NewFTTransactionDetails['id']>;
  trnxDetailsId: FormControl<IFTTransactionDetails['trnxDetailsId']>;
  transactionID: FormControl<IFTTransactionDetails['transactionID']>;
  debitAccountNumber: FormControl<IFTTransactionDetails['debitAccountNumber']>;
  creditAccountNumber: FormControl<IFTTransactionDetails['creditAccountNumber']>;
  remitterName: FormControl<IFTTransactionDetails['remitterName']>;
  amount: FormControl<IFTTransactionDetails['amount']>;
  currency: FormControl<IFTTransactionDetails['currency']>;
  transactionType: FormControl<IFTTransactionDetails['transactionType']>;
  paymentDescription: FormControl<IFTTransactionDetails['paymentDescription']>;
  beneficiaryIFSC: FormControl<IFTTransactionDetails['beneficiaryIFSC']>;
  beneficiaryName: FormControl<IFTTransactionDetails['beneficiaryName']>;
  beneficiaryAddress: FormControl<IFTTransactionDetails['beneficiaryAddress']>;
  emailId: FormControl<IFTTransactionDetails['emailId']>;
  mobileNo: FormControl<IFTTransactionDetails['mobileNo']>;
  transactionRefNo: FormControl<IFTTransactionDetails['transactionRefNo']>;
  trnxResourceDataStatus: FormControl<IFTTransactionDetails['trnxResourceDataStatus']>;
  trnxMetaDataStatus: FormControl<IFTTransactionDetails['trnxMetaDataStatus']>;
  transactionDateTime: FormControl<IFTTransactionDetails['transactionDateTime']>;
  disbursement: FormControl<IFTTransactionDetails['disbursement']>;
  repayment: FormControl<IFTTransactionDetails['repayment']>;
  participantsettlement: FormControl<IFTTransactionDetails['participantsettlement']>;
};

export type FTTransactionDetailsFormGroup = FormGroup<FTTransactionDetailsFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class FTTransactionDetailsFormService {
  createFTTransactionDetailsFormGroup(
    fTTransactionDetails: FTTransactionDetailsFormGroupInput = { id: null },
  ): FTTransactionDetailsFormGroup {
    const fTTransactionDetailsRawValue = {
      ...this.getFormDefaults(),
      ...fTTransactionDetails,
    };
    return new FormGroup<FTTransactionDetailsFormGroupContent>({
      id: new FormControl(
        { value: fTTransactionDetailsRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      trnxDetailsId: new FormControl(fTTransactionDetailsRawValue.trnxDetailsId, {
        validators: [Validators.required],
      }),
      transactionID: new FormControl(fTTransactionDetailsRawValue.transactionID, {
        validators: [Validators.required],
      }),
      debitAccountNumber: new FormControl(fTTransactionDetailsRawValue.debitAccountNumber, {
        validators: [Validators.required],
      }),
      creditAccountNumber: new FormControl(fTTransactionDetailsRawValue.creditAccountNumber, {
        validators: [Validators.required],
      }),
      remitterName: new FormControl(fTTransactionDetailsRawValue.remitterName, {
        validators: [Validators.required],
      }),
      amount: new FormControl(fTTransactionDetailsRawValue.amount, {
        validators: [Validators.required],
      }),
      currency: new FormControl(fTTransactionDetailsRawValue.currency, {
        validators: [Validators.required],
      }),
      transactionType: new FormControl(fTTransactionDetailsRawValue.transactionType, {
        validators: [Validators.required],
      }),
      paymentDescription: new FormControl(fTTransactionDetailsRawValue.paymentDescription, {
        validators: [Validators.required],
      }),
      beneficiaryIFSC: new FormControl(fTTransactionDetailsRawValue.beneficiaryIFSC, {
        validators: [Validators.required],
      }),
      beneficiaryName: new FormControl(fTTransactionDetailsRawValue.beneficiaryName, {
        validators: [Validators.required],
      }),
      beneficiaryAddress: new FormControl(fTTransactionDetailsRawValue.beneficiaryAddress, {
        validators: [Validators.required],
      }),
      emailId: new FormControl(fTTransactionDetailsRawValue.emailId, {
        validators: [Validators.required],
      }),
      mobileNo: new FormControl(fTTransactionDetailsRawValue.mobileNo, {
        validators: [Validators.required],
      }),
      transactionRefNo: new FormControl(fTTransactionDetailsRawValue.transactionRefNo, {
        validators: [Validators.required],
      }),
      trnxResourceDataStatus: new FormControl(fTTransactionDetailsRawValue.trnxResourceDataStatus, {
        validators: [Validators.required],
      }),
      trnxMetaDataStatus: new FormControl(fTTransactionDetailsRawValue.trnxMetaDataStatus, {
        validators: [Validators.required],
      }),
      transactionDateTime: new FormControl(fTTransactionDetailsRawValue.transactionDateTime, {
        validators: [Validators.required],
      }),
      disbursement: new FormControl(fTTransactionDetailsRawValue.disbursement),
      repayment: new FormControl(fTTransactionDetailsRawValue.repayment),
      participantsettlement: new FormControl(fTTransactionDetailsRawValue.participantsettlement),
    });
  }

  getFTTransactionDetails(form: FTTransactionDetailsFormGroup): IFTTransactionDetails | NewFTTransactionDetails {
    return form.getRawValue() as IFTTransactionDetails | NewFTTransactionDetails;
  }

  resetForm(form: FTTransactionDetailsFormGroup, fTTransactionDetails: FTTransactionDetailsFormGroupInput): void {
    const fTTransactionDetailsRawValue = { ...this.getFormDefaults(), ...fTTransactionDetails };
    form.reset(
      {
        ...fTTransactionDetailsRawValue,
        id: { value: fTTransactionDetailsRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): FTTransactionDetailsFormDefaults {
    return {
      id: null,
    };
  }
}
