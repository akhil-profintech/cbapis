import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ICREObservations, NewCREObservations } from '../cre-observations.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ICREObservations for edit and NewCREObservationsFormGroupInput for create.
 */
type CREObservationsFormGroupInput = ICREObservations | PartialWithRequiredKeyOf<NewCREObservations>;

type CREObservationsFormDefaults = Pick<NewCREObservations, 'id'>;

type CREObservationsFormGroupContent = {
  id: FormControl<ICREObservations['id'] | NewCREObservations['id']>;
  creObservationsId: FormControl<ICREObservations['creObservationsId']>;
  creObservationsUlidId: FormControl<ICREObservations['creObservationsUlidId']>;
  creRequestId: FormControl<ICREObservations['creRequestId']>;
  observations: FormControl<ICREObservations['observations']>;
  individualassessment: FormControl<ICREObservations['individualassessment']>;
};

export type CREObservationsFormGroup = FormGroup<CREObservationsFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class CREObservationsFormService {
  createCREObservationsFormGroup(cREObservations: CREObservationsFormGroupInput = { id: null }): CREObservationsFormGroup {
    const cREObservationsRawValue = {
      ...this.getFormDefaults(),
      ...cREObservations,
    };
    return new FormGroup<CREObservationsFormGroupContent>({
      id: new FormControl(
        { value: cREObservationsRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      creObservationsId: new FormControl(cREObservationsRawValue.creObservationsId),
      creObservationsUlidId: new FormControl(cREObservationsRawValue.creObservationsUlidId),
      creRequestId: new FormControl(cREObservationsRawValue.creRequestId),
      observations: new FormControl(cREObservationsRawValue.observations),
      individualassessment: new FormControl(cREObservationsRawValue.individualassessment),
    });
  }

  getCREObservations(form: CREObservationsFormGroup): ICREObservations | NewCREObservations {
    return form.getRawValue() as ICREObservations | NewCREObservations;
  }

  resetForm(form: CREObservationsFormGroup, cREObservations: CREObservationsFormGroupInput): void {
    const cREObservationsRawValue = { ...this.getFormDefaults(), ...cREObservations };
    form.reset(
      {
        ...cREObservationsRawValue,
        id: { value: cREObservationsRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): CREObservationsFormDefaults {
    return {
      id: null,
    };
  }
}
