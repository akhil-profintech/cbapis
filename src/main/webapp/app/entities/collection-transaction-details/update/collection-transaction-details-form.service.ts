import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ICollectionTransactionDetails, NewCollectionTransactionDetails } from '../collection-transaction-details.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ICollectionTransactionDetails for edit and NewCollectionTransactionDetailsFormGroupInput for create.
 */
type CollectionTransactionDetailsFormGroupInput = ICollectionTransactionDetails | PartialWithRequiredKeyOf<NewCollectionTransactionDetails>;

type CollectionTransactionDetailsFormDefaults = Pick<NewCollectionTransactionDetails, 'id'>;

type CollectionTransactionDetailsFormGroupContent = {
  id: FormControl<ICollectionTransactionDetails['id'] | NewCollectionTransactionDetails['id']>;
  trnxDetailsid: FormControl<ICollectionTransactionDetails['trnxDetailsid']>;
  customerCode: FormControl<ICollectionTransactionDetails['customerCode']>;
  customerName: FormControl<ICollectionTransactionDetails['customerName']>;
  productCode: FormControl<ICollectionTransactionDetails['productCode']>;
  transactionType: FormControl<ICollectionTransactionDetails['transactionType']>;
  batchAmt: FormControl<ICollectionTransactionDetails['batchAmt']>;
  batchAmtCcd: FormControl<ICollectionTransactionDetails['batchAmtCcd']>;
  creditDate: FormControl<ICollectionTransactionDetails['creditDate']>;
  vaNumber: FormControl<ICollectionTransactionDetails['vaNumber']>;
  utrNo: FormControl<ICollectionTransactionDetails['utrNo']>;
  creditGenerationTime: FormControl<ICollectionTransactionDetails['creditGenerationTime']>;
  remitterName: FormControl<ICollectionTransactionDetails['remitterName']>;
  remitterAccountNumber: FormControl<ICollectionTransactionDetails['remitterAccountNumber']>;
  ifscCode: FormControl<ICollectionTransactionDetails['ifscCode']>;
  disbursement: FormControl<ICollectionTransactionDetails['disbursement']>;
  repayment: FormControl<ICollectionTransactionDetails['repayment']>;
};

export type CollectionTransactionDetailsFormGroup = FormGroup<CollectionTransactionDetailsFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class CollectionTransactionDetailsFormService {
  createCollectionTransactionDetailsFormGroup(
    collectionTransactionDetails: CollectionTransactionDetailsFormGroupInput = { id: null },
  ): CollectionTransactionDetailsFormGroup {
    const collectionTransactionDetailsRawValue = {
      ...this.getFormDefaults(),
      ...collectionTransactionDetails,
    };
    return new FormGroup<CollectionTransactionDetailsFormGroupContent>({
      id: new FormControl(
        { value: collectionTransactionDetailsRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      trnxDetailsid: new FormControl(collectionTransactionDetailsRawValue.trnxDetailsid, {
        validators: [Validators.required],
      }),
      customerCode: new FormControl(collectionTransactionDetailsRawValue.customerCode, {
        validators: [Validators.required],
      }),
      customerName: new FormControl(collectionTransactionDetailsRawValue.customerName, {
        validators: [Validators.required],
      }),
      productCode: new FormControl(collectionTransactionDetailsRawValue.productCode, {
        validators: [Validators.required],
      }),
      transactionType: new FormControl(collectionTransactionDetailsRawValue.transactionType, {
        validators: [Validators.required],
      }),
      batchAmt: new FormControl(collectionTransactionDetailsRawValue.batchAmt, {
        validators: [Validators.required],
      }),
      batchAmtCcd: new FormControl(collectionTransactionDetailsRawValue.batchAmtCcd, {
        validators: [Validators.required],
      }),
      creditDate: new FormControl(collectionTransactionDetailsRawValue.creditDate, {
        validators: [Validators.required],
      }),
      vaNumber: new FormControl(collectionTransactionDetailsRawValue.vaNumber, {
        validators: [Validators.required],
      }),
      utrNo: new FormControl(collectionTransactionDetailsRawValue.utrNo, {
        validators: [Validators.required],
      }),
      creditGenerationTime: new FormControl(collectionTransactionDetailsRawValue.creditGenerationTime, {
        validators: [Validators.required],
      }),
      remitterName: new FormControl(collectionTransactionDetailsRawValue.remitterName, {
        validators: [Validators.required],
      }),
      remitterAccountNumber: new FormControl(collectionTransactionDetailsRawValue.remitterAccountNumber, {
        validators: [Validators.required],
      }),
      ifscCode: new FormControl(collectionTransactionDetailsRawValue.ifscCode, {
        validators: [Validators.required],
      }),
      disbursement: new FormControl(collectionTransactionDetailsRawValue.disbursement),
      repayment: new FormControl(collectionTransactionDetailsRawValue.repayment),
    });
  }

  getCollectionTransactionDetails(
    form: CollectionTransactionDetailsFormGroup,
  ): ICollectionTransactionDetails | NewCollectionTransactionDetails {
    return form.getRawValue() as ICollectionTransactionDetails | NewCollectionTransactionDetails;
  }

  resetForm(form: CollectionTransactionDetailsFormGroup, collectionTransactionDetails: CollectionTransactionDetailsFormGroupInput): void {
    const collectionTransactionDetailsRawValue = { ...this.getFormDefaults(), ...collectionTransactionDetails };
    form.reset(
      {
        ...collectionTransactionDetailsRawValue,
        id: { value: collectionTransactionDetailsRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): CollectionTransactionDetailsFormDefaults {
    return {
      id: null,
    };
  }
}
