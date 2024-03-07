import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IDisbursement, NewDisbursement } from '../disbursement.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IDisbursement for edit and NewDisbursementFormGroupInput for create.
 */
type DisbursementFormGroupInput = IDisbursement | PartialWithRequiredKeyOf<NewDisbursement>;

type DisbursementFormDefaults = Pick<NewDisbursement, 'id'>;

type DisbursementFormGroupContent = {
  id: FormControl<IDisbursement['id'] | NewDisbursement['id']>;
  dbmtId: FormControl<IDisbursement['dbmtId']>;
  disbursementUlidId: FormControl<IDisbursement['disbursementUlidId']>;
  disbursementRefNo: FormControl<IDisbursement['disbursementRefNo']>;
  acceptedOfferUlidId: FormControl<IDisbursement['acceptedOfferUlidId']>;
  placedOfferUlidId: FormControl<IDisbursement['placedOfferUlidId']>;
  reqOffUlidId: FormControl<IDisbursement['reqOffUlidId']>;
  offerAcceptedDate: FormControl<IDisbursement['offerAcceptedDate']>;
  dbmtRequestId: FormControl<IDisbursement['dbmtRequestId']>;
  dbmtReqAmount: FormControl<IDisbursement['dbmtReqAmount']>;
  currency: FormControl<IDisbursement['currency']>;
  dbmtRequestDate: FormControl<IDisbursement['dbmtRequestDate']>;
  dbmtStatus: FormControl<IDisbursement['dbmtStatus']>;
  offerStatus: FormControl<IDisbursement['offerStatus']>;
  docId: FormControl<IDisbursement['docId']>;
  dbmtDateTime: FormControl<IDisbursement['dbmtDateTime']>;
  dbmtAmount: FormControl<IDisbursement['dbmtAmount']>;
  financeRequestId: FormControl<IDisbursement['financeRequestId']>;
  amountToBeDisbursed: FormControl<IDisbursement['amountToBeDisbursed']>;
  destinationAccountName: FormControl<IDisbursement['destinationAccountName']>;
  destinationAccountNumber: FormControl<IDisbursement['destinationAccountNumber']>;
  status: FormControl<IDisbursement['status']>;
  actionByDate: FormControl<IDisbursement['actionByDate']>;
  financerequest: FormControl<IDisbursement['financerequest']>;
  financepartner: FormControl<IDisbursement['financepartner']>;
};

export type DisbursementFormGroup = FormGroup<DisbursementFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class DisbursementFormService {
  createDisbursementFormGroup(disbursement: DisbursementFormGroupInput = { id: null }): DisbursementFormGroup {
    const disbursementRawValue = {
      ...this.getFormDefaults(),
      ...disbursement,
    };
    return new FormGroup<DisbursementFormGroupContent>({
      id: new FormControl(
        { value: disbursementRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      dbmtId: new FormControl(disbursementRawValue.dbmtId),
      disbursementUlidId: new FormControl(disbursementRawValue.disbursementUlidId),
      disbursementRefNo: new FormControl(disbursementRawValue.disbursementRefNo),
      acceptedOfferUlidId: new FormControl(disbursementRawValue.acceptedOfferUlidId, {
        validators: [Validators.required],
      }),
      placedOfferUlidId: new FormControl(disbursementRawValue.placedOfferUlidId, {
        validators: [Validators.required],
      }),
      reqOffUlidId: new FormControl(disbursementRawValue.reqOffUlidId, {
        validators: [Validators.required],
      }),
      offerAcceptedDate: new FormControl(disbursementRawValue.offerAcceptedDate, {
        validators: [Validators.required],
      }),
      dbmtRequestId: new FormControl(disbursementRawValue.dbmtRequestId, {
        validators: [Validators.required],
      }),
      dbmtReqAmount: new FormControl(disbursementRawValue.dbmtReqAmount, {
        validators: [Validators.required],
      }),
      currency: new FormControl(disbursementRawValue.currency, {
        validators: [Validators.required],
      }),
      dbmtRequestDate: new FormControl(disbursementRawValue.dbmtRequestDate, {
        validators: [Validators.required],
      }),
      dbmtStatus: new FormControl(disbursementRawValue.dbmtStatus, {
        validators: [Validators.required],
      }),
      offerStatus: new FormControl(disbursementRawValue.offerStatus),
      docId: new FormControl(disbursementRawValue.docId),
      dbmtDateTime: new FormControl(disbursementRawValue.dbmtDateTime),
      dbmtAmount: new FormControl(disbursementRawValue.dbmtAmount),
      financeRequestId: new FormControl(disbursementRawValue.financeRequestId),
      amountToBeDisbursed: new FormControl(disbursementRawValue.amountToBeDisbursed),
      destinationAccountName: new FormControl(disbursementRawValue.destinationAccountName),
      destinationAccountNumber: new FormControl(disbursementRawValue.destinationAccountNumber),
      status: new FormControl(disbursementRawValue.status),
      actionByDate: new FormControl(disbursementRawValue.actionByDate),
      financerequest: new FormControl(disbursementRawValue.financerequest),
      financepartner: new FormControl(disbursementRawValue.financepartner),
    });
  }

  getDisbursement(form: DisbursementFormGroup): IDisbursement | NewDisbursement {
    return form.getRawValue() as IDisbursement | NewDisbursement;
  }

  resetForm(form: DisbursementFormGroup, disbursement: DisbursementFormGroupInput): void {
    const disbursementRawValue = { ...this.getFormDefaults(), ...disbursement };
    form.reset(
      {
        ...disbursementRawValue,
        id: { value: disbursementRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): DisbursementFormDefaults {
    return {
      id: null,
    };
  }
}
