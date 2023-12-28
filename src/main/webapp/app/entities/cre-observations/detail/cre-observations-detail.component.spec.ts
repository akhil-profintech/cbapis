import { TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness, RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { CREObservationsDetailComponent } from './cre-observations-detail.component';

describe('CREObservations Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CREObservationsDetailComponent, RouterTestingModule.withRoutes([], { bindToComponentInputs: true })],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: CREObservationsDetailComponent,
              resolve: { cREObservations: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(CREObservationsDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load cREObservations on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', CREObservationsDetailComponent);

      // THEN
      expect(instance.cREObservations).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
