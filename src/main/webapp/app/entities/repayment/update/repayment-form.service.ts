import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IRepayment, NewRepayment } from '../repayment.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IRepayment for edit and NewRepaymentFormGroupInput for create.
 */
type RepaymentFormGroupInput = IRepayment | PartialWithRequiredKeyOf<NewRepayment>;

type RepaymentFormDefaults = Pick<NewRepayment, 'id'>;

type RepaymentFormGroupContent = {
  id: FormControl<IRepayment['id'] | NewRepayment['id']>;
  repaymentId: FormControl<IRepayment['repaymentId']>;
  repaymentUlidId: FormControl<IRepayment['repaymentUlidId']>;
  repaymentRefNo: FormControl<IRepayment['repaymentRefNo']>;
  acceptedOfferUlidId: FormControl<IRepayment['acceptedOfferUlidId']>;
  placedOfferUlidId: FormControl<IRepayment['placedOfferUlidId']>;
  reqOffUlidId: FormControl<IRepayment['reqOffUlidId']>;
  offerAcceptedDate: FormControl<IRepayment['offerAcceptedDate']>;
  dbmtRequestId: FormControl<IRepayment['dbmtRequestId']>;
  dbmtStatus: FormControl<IRepayment['dbmtStatus']>;
  dbmtDateTime: FormControl<IRepayment['dbmtDateTime']>;
  dbmtId: FormControl<IRepayment['dbmtId']>;
  dbmtAmount: FormControl<IRepayment['dbmtAmount']>;
  currency: FormControl<IRepayment['currency']>;
  repaymentStatus: FormControl<IRepayment['repaymentStatus']>;
  repaymentDate: FormControl<IRepayment['repaymentDate']>;
  repaymentAmount: FormControl<IRepayment['repaymentAmount']>;
  financeRequestId: FormControl<IRepayment['financeRequestId']>;
  repaymentDueDate: FormControl<IRepayment['repaymentDueDate']>;
  totalRepaymentAmount: FormControl<IRepayment['totalRepaymentAmount']>;
  amountRepayed: FormControl<IRepayment['amountRepayed']>;
  amountToBePaid: FormControl<IRepayment['amountToBePaid']>;
  sourceAccountName: FormControl<IRepayment['sourceAccountName']>;
  sourceAccountNumber: FormControl<IRepayment['sourceAccountNumber']>;
  ifscCode: FormControl<IRepayment['ifscCode']>;
  status: FormControl<IRepayment['status']>;
  referenceNumber: FormControl<IRepayment['referenceNumber']>;
  financerequest: FormControl<IRepayment['financerequest']>;
};

export type RepaymentFormGroup = FormGroup<RepaymentFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class RepaymentFormService {
  createRepaymentFormGroup(repayment: RepaymentFormGroupInput = { id: null }): RepaymentFormGroup {
    const repaymentRawValue = {
      ...this.getFormDefaults(),
      ...repayment,
    };
    return new FormGroup<RepaymentFormGroupContent>({
      id: new FormControl(
        { value: repaymentRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      repaymentId: new FormControl(repaymentRawValue.repaymentId),
      repaymentUlidId: new FormControl(repaymentRawValue.repaymentUlidId),
      repaymentRefNo: new FormControl(repaymentRawValue.repaymentRefNo),
      acceptedOfferUlidId: new FormControl(repaymentRawValue.acceptedOfferUlidId, {
        validators: [Validators.required],
      }),
      placedOfferUlidId: new FormControl(repaymentRawValue.placedOfferUlidId, {
        validators: [Validators.required],
      }),
      reqOffUlidId: new FormControl(repaymentRawValue.reqOffUlidId, {
        validators: [Validators.required],
      }),
      offerAcceptedDate: new FormControl(repaymentRawValue.offerAcceptedDate, {
        validators: [Validators.required],
      }),
      dbmtRequestId: new FormControl(repaymentRawValue.dbmtRequestId, {
        validators: [Validators.required],
      }),
      dbmtStatus: new FormControl(repaymentRawValue.dbmtStatus, {
        validators: [Validators.required],
      }),
      dbmtDateTime: new FormControl(repaymentRawValue.dbmtDateTime, {
        validators: [Validators.required],
      }),
      dbmtId: new FormControl(repaymentRawValue.dbmtId, {
        validators: [Validators.required],
      }),
      dbmtAmount: new FormControl(repaymentRawValue.dbmtAmount, {
        validators: [Validators.required],
      }),
      currency: new FormControl(repaymentRawValue.currency, {
        validators: [Validators.required],
      }),
      repaymentStatus: new FormControl(repaymentRawValue.repaymentStatus, {
        validators: [Validators.required],
      }),
      repaymentDate: new FormControl(repaymentRawValue.repaymentDate),
      repaymentAmount: new FormControl(repaymentRawValue.repaymentAmount),
      financeRequestId: new FormControl(repaymentRawValue.financeRequestId),
      repaymentDueDate: new FormControl(repaymentRawValue.repaymentDueDate),
      totalRepaymentAmount: new FormControl(repaymentRawValue.totalRepaymentAmount),
      amountRepayed: new FormControl(repaymentRawValue.amountRepayed),
      amountToBePaid: new FormControl(repaymentRawValue.amountToBePaid),
      sourceAccountName: new FormControl(repaymentRawValue.sourceAccountName),
      sourceAccountNumber: new FormControl(repaymentRawValue.sourceAccountNumber),
      ifscCode: new FormControl(repaymentRawValue.ifscCode),
      status: new FormControl(repaymentRawValue.status),
      referenceNumber: new FormControl(repaymentRawValue.referenceNumber),
      financerequest: new FormControl(repaymentRawValue.financerequest),
    });
  }

  getRepayment(form: RepaymentFormGroup): IRepayment | NewRepayment {
    return form.getRawValue() as IRepayment | NewRepayment;
  }

  resetForm(form: RepaymentFormGroup, repayment: RepaymentFormGroupInput): void {
    const repaymentRawValue = { ...this.getFormDefaults(), ...repayment };
    form.reset(
      {
        ...repaymentRawValue,
        id: { value: repaymentRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): RepaymentFormDefaults {
    return {
      id: null,
    };
  }
}
