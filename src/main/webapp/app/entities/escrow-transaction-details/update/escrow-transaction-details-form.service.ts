import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IEscrowTransactionDetails, NewEscrowTransactionDetails } from '../escrow-transaction-details.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IEscrowTransactionDetails for edit and NewEscrowTransactionDetailsFormGroupInput for create.
 */
type EscrowTransactionDetailsFormGroupInput = IEscrowTransactionDetails | PartialWithRequiredKeyOf<NewEscrowTransactionDetails>;

type EscrowTransactionDetailsFormDefaults = Pick<NewEscrowTransactionDetails, 'id'>;

type EscrowTransactionDetailsFormGroupContent = {
  id: FormControl<IEscrowTransactionDetails['id'] | NewEscrowTransactionDetails['id']>;
  escrowTrnxDetailsId: FormControl<IEscrowTransactionDetails['escrowTrnxDetailsId']>;
  escrowTrnxDetailsUlidId: FormControl<IEscrowTransactionDetails['escrowTrnxDetailsUlidId']>;
  customerCode: FormControl<IEscrowTransactionDetails['customerCode']>;
  customerName: FormControl<IEscrowTransactionDetails['customerName']>;
  productCode: FormControl<IEscrowTransactionDetails['productCode']>;
  transactionType: FormControl<IEscrowTransactionDetails['transactionType']>;
  batchAmt: FormControl<IEscrowTransactionDetails['batchAmt']>;
  batchAmtCcd: FormControl<IEscrowTransactionDetails['batchAmtCcd']>;
  creditDate: FormControl<IEscrowTransactionDetails['creditDate']>;
  vaNumber: FormControl<IEscrowTransactionDetails['vaNumber']>;
  utrNo: FormControl<IEscrowTransactionDetails['utrNo']>;
  creditGenerationTime: FormControl<IEscrowTransactionDetails['creditGenerationTime']>;
  remitterName: FormControl<IEscrowTransactionDetails['remitterName']>;
  remitterAccountNumber: FormControl<IEscrowTransactionDetails['remitterAccountNumber']>;
  ifscCode: FormControl<IEscrowTransactionDetails['ifscCode']>;
  disbursement: FormControl<IEscrowTransactionDetails['disbursement']>;
  repayment: FormControl<IEscrowTransactionDetails['repayment']>;
};

export type EscrowTransactionDetailsFormGroup = FormGroup<EscrowTransactionDetailsFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class EscrowTransactionDetailsFormService {
  createEscrowTransactionDetailsFormGroup(
    escrowTransactionDetails: EscrowTransactionDetailsFormGroupInput = { id: null },
  ): EscrowTransactionDetailsFormGroup {
    const escrowTransactionDetailsRawValue = {
      ...this.getFormDefaults(),
      ...escrowTransactionDetails,
    };
    return new FormGroup<EscrowTransactionDetailsFormGroupContent>({
      id: new FormControl(
        { value: escrowTransactionDetailsRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      escrowTrnxDetailsId: new FormControl(escrowTransactionDetailsRawValue.escrowTrnxDetailsId),
      escrowTrnxDetailsUlidId: new FormControl(escrowTransactionDetailsRawValue.escrowTrnxDetailsUlidId),
      customerCode: new FormControl(escrowTransactionDetailsRawValue.customerCode, {
        validators: [Validators.required],
      }),
      customerName: new FormControl(escrowTransactionDetailsRawValue.customerName, {
        validators: [Validators.required],
      }),
      productCode: new FormControl(escrowTransactionDetailsRawValue.productCode, {
        validators: [Validators.required],
      }),
      transactionType: new FormControl(escrowTransactionDetailsRawValue.transactionType, {
        validators: [Validators.required],
      }),
      batchAmt: new FormControl(escrowTransactionDetailsRawValue.batchAmt, {
        validators: [Validators.required],
      }),
      batchAmtCcd: new FormControl(escrowTransactionDetailsRawValue.batchAmtCcd, {
        validators: [Validators.required],
      }),
      creditDate: new FormControl(escrowTransactionDetailsRawValue.creditDate, {
        validators: [Validators.required],
      }),
      vaNumber: new FormControl(escrowTransactionDetailsRawValue.vaNumber, {
        validators: [Validators.required],
      }),
      utrNo: new FormControl(escrowTransactionDetailsRawValue.utrNo, {
        validators: [Validators.required],
      }),
      creditGenerationTime: new FormControl(escrowTransactionDetailsRawValue.creditGenerationTime, {
        validators: [Validators.required],
      }),
      remitterName: new FormControl(escrowTransactionDetailsRawValue.remitterName, {
        validators: [Validators.required],
      }),
      remitterAccountNumber: new FormControl(escrowTransactionDetailsRawValue.remitterAccountNumber, {
        validators: [Validators.required],
      }),
      ifscCode: new FormControl(escrowTransactionDetailsRawValue.ifscCode, {
        validators: [Validators.required],
      }),
      disbursement: new FormControl(escrowTransactionDetailsRawValue.disbursement),
      repayment: new FormControl(escrowTransactionDetailsRawValue.repayment),
    });
  }

  getEscrowTransactionDetails(form: EscrowTransactionDetailsFormGroup): IEscrowTransactionDetails | NewEscrowTransactionDetails {
    return form.getRawValue() as IEscrowTransactionDetails | NewEscrowTransactionDetails;
  }

  resetForm(form: EscrowTransactionDetailsFormGroup, escrowTransactionDetails: EscrowTransactionDetailsFormGroupInput): void {
    const escrowTransactionDetailsRawValue = { ...this.getFormDefaults(), ...escrowTransactionDetails };
    form.reset(
      {
        ...escrowTransactionDetailsRawValue,
        id: { value: escrowTransactionDetailsRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): EscrowTransactionDetailsFormDefaults {
    return {
      id: null,
    };
  }
}
