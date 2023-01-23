import {render, screen} from '@testing-library/react';
import {Percentages} from './percentages';

test('renders learn react link', () => {
  render(<Percentages />);
  const element = screen.getByText(/Build your components here/i);
  expect(element).toBeInTheDocument();
});
