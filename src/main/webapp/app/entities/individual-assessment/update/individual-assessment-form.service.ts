import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IIndividualAssessment, NewIndividualAssessment } from '../individual-assessment.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IIndividualAssessment for edit and NewIndividualAssessmentFormGroupInput for create.
 */
type IndividualAssessmentFormGroupInput = IIndividualAssessment | PartialWithRequiredKeyOf<NewIndividualAssessment>;

type IndividualAssessmentFormDefaults = Pick<NewIndividualAssessment, 'id'>;

type IndividualAssessmentFormGroupContent = {
  id: FormControl<IIndividualAssessment['id'] | NewIndividualAssessment['id']>;
  assessmentId: FormControl<IIndividualAssessment['assessmentId']>;
  assessmentUlidId: FormControl<IIndividualAssessment['assessmentUlidId']>;
  creditScore: FormControl<IIndividualAssessment['creditScore']>;
  finalVerdict: FormControl<IIndividualAssessment['finalVerdict']>;
  creRequestId: FormControl<IIndividualAssessment['creRequestId']>;
  timestamp: FormControl<IIndividualAssessment['timestamp']>;
  tradePartnerGST: FormControl<IIndividualAssessment['tradePartnerGST']>;
  tradePartnerId: FormControl<IIndividualAssessment['tradePartnerId']>;
  invoiceAmount: FormControl<IIndividualAssessment['invoiceAmount']>;
  invoiceId: FormControl<IIndividualAssessment['invoiceId']>;
  cbcreprocess: FormControl<IIndividualAssessment['cbcreprocess']>;
};

export type IndividualAssessmentFormGroup = FormGroup<IndividualAssessmentFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class IndividualAssessmentFormService {
  createIndividualAssessmentFormGroup(
    individualAssessment: IndividualAssessmentFormGroupInput = { id: null },
  ): IndividualAssessmentFormGroup {
    const individualAssessmentRawValue = {
      ...this.getFormDefaults(),
      ...individualAssessment,
    };
    return new FormGroup<IndividualAssessmentFormGroupContent>({
      id: new FormControl(
        { value: individualAssessmentRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      assessmentId: new FormControl(individualAssessmentRawValue.assessmentId),
      assessmentUlidId: new FormControl(individualAssessmentRawValue.assessmentUlidId),
      creditScore: new FormControl(individualAssessmentRawValue.creditScore),
      finalVerdict: new FormControl(individualAssessmentRawValue.finalVerdict),
      creRequestId: new FormControl(individualAssessmentRawValue.creRequestId),
      timestamp: new FormControl(individualAssessmentRawValue.timestamp),
      tradePartnerGST: new FormControl(individualAssessmentRawValue.tradePartnerGST),
      tradePartnerId: new FormControl(individualAssessmentRawValue.tradePartnerId),
      invoiceAmount: new FormControl(individualAssessmentRawValue.invoiceAmount),
      invoiceId: new FormControl(individualAssessmentRawValue.invoiceId),
      cbcreprocess: new FormControl(individualAssessmentRawValue.cbcreprocess),
    });
  }

  getIndividualAssessment(form: IndividualAssessmentFormGroup): IIndividualAssessment | NewIndividualAssessment {
    return form.getRawValue() as IIndividualAssessment | NewIndividualAssessment;
  }

  resetForm(form: IndividualAssessmentFormGroup, individualAssessment: IndividualAssessmentFormGroupInput): void {
    const individualAssessmentRawValue = { ...this.getFormDefaults(), ...individualAssessment };
    form.reset(
      {
        ...individualAssessmentRawValue,
        id: { value: individualAssessmentRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): IndividualAssessmentFormDefaults {
    return {
      id: null,
    };
  }
}
