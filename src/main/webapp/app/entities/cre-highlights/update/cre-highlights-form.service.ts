import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ICREHighlights, NewCREHighlights } from '../cre-highlights.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ICREHighlights for edit and NewCREHighlightsFormGroupInput for create.
 */
type CREHighlightsFormGroupInput = ICREHighlights | PartialWithRequiredKeyOf<NewCREHighlights>;

type CREHighlightsFormDefaults = Pick<NewCREHighlights, 'id'>;

type CREHighlightsFormGroupContent = {
  id: FormControl<ICREHighlights['id'] | NewCREHighlights['id']>;
  creHighlightsId: FormControl<ICREHighlights['creHighlightsId']>;
  creHighlightsUlidId: FormControl<ICREHighlights['creHighlightsUlidId']>;
  creRequestId: FormControl<ICREHighlights['creRequestId']>;
  highlights: FormControl<ICREHighlights['highlights']>;
  individualassessment: FormControl<ICREHighlights['individualassessment']>;
};

export type CREHighlightsFormGroup = FormGroup<CREHighlightsFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class CREHighlightsFormService {
  createCREHighlightsFormGroup(cREHighlights: CREHighlightsFormGroupInput = { id: null }): CREHighlightsFormGroup {
    const cREHighlightsRawValue = {
      ...this.getFormDefaults(),
      ...cREHighlights,
    };
    return new FormGroup<CREHighlightsFormGroupContent>({
      id: new FormControl(
        { value: cREHighlightsRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      creHighlightsId: new FormControl(cREHighlightsRawValue.creHighlightsId),
      creHighlightsUlidId: new FormControl(cREHighlightsRawValue.creHighlightsUlidId),
      creRequestId: new FormControl(cREHighlightsRawValue.creRequestId),
      highlights: new FormControl(cREHighlightsRawValue.highlights),
      individualassessment: new FormControl(cREHighlightsRawValue.individualassessment),
    });
  }

  getCREHighlights(form: CREHighlightsFormGroup): ICREHighlights | NewCREHighlights {
    return form.getRawValue() as ICREHighlights | NewCREHighlights;
  }

  resetForm(form: CREHighlightsFormGroup, cREHighlights: CREHighlightsFormGroupInput): void {
    const cREHighlightsRawValue = { ...this.getFormDefaults(), ...cREHighlights };
    form.reset(
      {
        ...cREHighlightsRawValue,
        id: { value: cREHighlightsRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): CREHighlightsFormDefaults {
    return {
      id: null,
    };
  }
}
