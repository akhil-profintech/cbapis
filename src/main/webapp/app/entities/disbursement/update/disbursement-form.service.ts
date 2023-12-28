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
  disbursementRefNo: FormControl<IDisbursement['disbursementRefNo']>;
  acceptedOfferId: FormControl<IDisbursement['acceptedOfferId']>;
  offerId: FormControl<IDisbursement['offerId']>;
  offerAcceptedDate: FormControl<IDisbursement['offerAcceptedDate']>;
  dbmtRequestId: FormControl<IDisbursement['dbmtRequestId']>;
  dbmtReqAmount: FormControl<IDisbursement['dbmtReqAmount']>;
  currency: FormControl<IDisbursement['currency']>;
  dbmtRequestDate: FormControl<IDisbursement['dbmtRequestDate']>;
  dbmtstatus: FormControl<IDisbursement['dbmtstatus']>;
  offerStatus: FormControl<IDisbursement['offerStatus']>;
  ftTrnxDetailsId: FormControl<IDisbursement['ftTrnxDetailsId']>;
  collectionTrnxDetailsId: FormControl<IDisbursement['collectionTrnxDetailsId']>;
  docId: FormControl<IDisbursement['docId']>;
  dbmtDateTime: FormControl<IDisbursement['dbmtDateTime']>;
  dbmtAmount: FormControl<IDisbursement['dbmtAmount']>;
  financeRequestId: FormControl<IDisbursement['financeRequestId']>;
  amountToBeDisbursed: FormControl<IDisbursement['amountToBeDisbursed']>;
  destinationAccountName: FormControl<IDisbursement['destinationAccountName']>;
  destinationAccountNumber: FormControl<IDisbursement['destinationAccountNumber']>;
  ifscCode: FormControl<IDisbursement['ifscCode']>;
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
      disbursementRefNo: new FormControl(disbursementRawValue.disbursementRefNo),
      acceptedOfferId: new FormControl(disbursementRawValue.acceptedOfferId, {
        validators: [Validators.required],
      }),
      offerId: new FormControl(disbursementRawValue.offerId, {
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
      dbmtstatus: new FormControl(disbursementRawValue.dbmtstatus, {
        validators: [Validators.required],
      }),
      offerStatus: new FormControl(disbursementRawValue.offerStatus),
      ftTrnxDetailsId: new FormControl(disbursementRawValue.ftTrnxDetailsId),
      collectionTrnxDetailsId: new FormControl(disbursementRawValue.collectionTrnxDetailsId),
      docId: new FormControl(disbursementRawValue.docId),
      dbmtDateTime: new FormControl(disbursementRawValue.dbmtDateTime),
      dbmtAmount: new FormControl(disbursementRawValue.dbmtAmount),
      financeRequestId: new FormControl(disbursementRawValue.financeRequestId),
      amountToBeDisbursed: new FormControl(disbursementRawValue.amountToBeDisbursed),
      destinationAccountName: new FormControl(disbursementRawValue.destinationAccountName),
      destinationAccountNumber: new FormControl(disbursementRawValue.destinationAccountNumber),
      ifscCode: new FormControl(disbursementRawValue.ifscCode),
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
