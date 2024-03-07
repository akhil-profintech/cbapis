import { TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness, RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { GstinDetailComponent } from './gstin-detail.component';

describe('Gstin Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GstinDetailComponent, RouterTestingModule.withRoutes([], { bindToComponentInputs: true })],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: GstinDetailComponent,
              resolve: { gstin: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(GstinDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load gstin on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', GstinDetailComponent);

      // THEN
      expect(instance.gstin).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
